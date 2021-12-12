<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div class="app-container">
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="searchObj.username" placeholder="用户名"/>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>
    <!-- 工具条 -->
    <div>
      <el-button type="danger" size="mini" @click="addUser()">添加</el-button>
      <el-button type="danger" size="mini" @click="removeRows()">批量删除</el-button>
    </div>
    <!--用户列表 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      stripe
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
      />
      <el-table-column
        label="序号"
        width="70"
        align="center"
      >
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="username" label="用户名" width="150"/>
      <el-table-column prop="nickName" label="用户昵称"/>
      <el-table-column prop="gmtCreate" label="创建时间" width="180"/>
      <el-table-column label="操作" width="250" align="center">
        <template slot-scope="scope">
          <router-link v-hasPer="['user.assgin']" :to="'/authority/user/role/'+scope.row.id">
            <el-button type="success" size="mini">绑定角色</el-button>
          </router-link>
          &nbsp;
          <router-link v-hasPer="['user.update']" :to="'/authority/user/update/'+scope.row.id">
            <el-button type="primary" size="mini">修改</el-button>
          </router-link>
          &nbsp;
          <el-button v-hasPer="['user.remove']" type="danger" size="mini" @click="removeDataById(scope.row.id)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="page"
      :total="total"
      :page-size="limit"
      :page-sizes="[5, 10, 20, 30, 40, 50, 100]"
      style="padding: 30px 0; text-align: center;"
      layout="sizes, prev, pager, next, jumper, ->, total, slot"
      @current-change="fetchData"
      @size-change="changeSize"
    />

    <el-dialog :width="overWidth + '%'" :visible.sync="previewPdfOpen" append-to-body>
      <div class="dialog-body">
        <!--        <embed style="width:100%;height:900px" :src="pdfUrl">-->
        <!--        <iframe style="width:100%;height:900pxpx" :src="pdfUrl" ></iframe>-->
        <div class="word-wrap">
          <div id="wordView" v-html="wordText"/>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <!--        <el-button @click="overMinMethod()">缩 小</el-button>-->
        <!--        <el-button @click="overMaxMethod()">放 大</el-button>-->
        <el-button @click="previewPdfOpen = false">取 消</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import user from '@/api/authority/user'
import mammoth from 'mammoth'

export default {
  data() {
    return {
      previewPdfOpen: false,
      pdfUrl: '',
      overWidth: '60',
      wordText: '',
      wordURL: 'http://192.168.30.74:9000/archives/202110/f3ec09ad-c313-4b5b-9dbf-5750e5b3073c.docx', // 文件地址
      listLoading: true, // 数据是否正在加载
      list: null, // 用户列表
      total: 0, // 数据库中的总记录数
      page: 1, // 默认页码
      limit: 10, // 每页记录数
      searchObj: {}, // 查询表单对象
      multipleSelection: [], // 批量选择中选择的记录列表
      websocket: null // ws 服务
    }
  },
  created() {
    /** 加载页面数据*/
    this.fetchData()
    this.connection()
    // this.getWordText()
  },
  destroyed() {
    if (this.websocket != null) this.websocket.close() // 离开路由之后断开websocket连接
  },
  methods: {
    /**
     * 连接后台ws
     */
    connection() {
      const socketUrl = process.env.VUE_APP_BASE_WS_API + this.$store.getters.name
      if (typeof (WebSocket) === 'undefined') {
        console.log('您的浏览器不支持WebSocket')
        this.$message.error('您的浏览器不支持WebSocket,无法使用推送功能!')
      } else {
        this.websocket = new WebSocket(socketUrl)
        console.log(this.websocket)
        this.websocket.onopen = this.websocketOnopen // 连接成功
        this.websocket.onmessage = this.websocketOnmessage // 广播成功
        this.websocket.onerror = this.websocketOnerror // 连接断开，失败
        this.websocket.onclose = this.websocketClose // 连接关闭
      }
    },
    websocketOnopen() {
      console.log('连接成功')
    },
    websocketOnerror() {
      console.log('连接失败')
    },
    websocketClose() {
      console.log('断开连接')
    },
    websocketOnmessage(data) {
      console.log(data)
    },
    getWordText() {
      // mammoth.convertToHtml({ path: 'http://192.168.30.74:9000/archives/202110/f076c704-785a-4857-a517-98b99e05d1b1.docx' })
      //   .then(function(result) {
      //     console.log(result)
      //     this.previewPdfOpen = true
      //     this.pdfUrl = result.value
      //   })
      //   .done()
      const xhr = new XMLHttpRequest()
      xhr.open('get', this.wordURL, true)
      xhr.responseType = 'arraybuffer'
      xhr.onload = () => {
        console.log(xhr)
        if (xhr.status === 200) {
          mammoth.convertToHtml({ arrayBuffer: new Uint8Array(xhr.response) }).then((resultObject) => {
            console.log(resultObject)
            this.$nextTick(() => {
              this.wordText = resultObject.value
              this.previewPdfOpen = true
            })
          })
        }
      }
      xhr.send()
    },
    /** 当页码发生改变的时候*/
    changeSize(size) {
      console.log(size)
      this.limit = size
      this.fetchData(1)
    },
    addUser() {
      /** 添加用户,跳转到添加路由*/
      this.$router.push({ path: '/authority/user/add' })
    },

    /** 加载用户数据*/
    fetchData(page = 1) {
      this.page = page
      user.getPageList(this.page, this.limit, this.searchObj).then(
        response => {
          this.list = response.data.rows
          this.total = response.data.total
          // 数据加载并绑定成功
          this.listLoading = false
        }
      )
    },
    /** 重置查询表单*/
    resetData() {
      console.log('重置查询表单')
      this.searchObj = {}
      this.fetchData()
    },
    /** 根据id删除数据*/
    removeDataById(id) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => { // promise
        // 点击确定，远程调用ajax
        return user.removeById(id)
      }).then((response) => {
        this.fetchData(this.page)/** 重新加载列表*/
        if (response.success) {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    /** 当表格复选框选项发生变化的时候触发*/
    handleSelectionChange(selection) {
      this.multipleSelection = selection
    },
    /** 批量删除*/
    removeRows() {
      if (this.multipleSelection.length === 0) {
        this.$message({
          type: 'warning',
          message: '请选择要删除的记录!'
        })
        return
      }
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        var idList = []
        this.multipleSelection.forEach(item => {
          idList.push(item.id)
        })
        // 调用api
        return user.removeRows(idList)
      }).then((response) => {
        this.fetchData(this.page)
        if (response.success) {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
</script>

<style scoped>
.word-wrap {
  padding: 15px;
}

.word-wrap > img {
  width: 100%;
}
</style>
