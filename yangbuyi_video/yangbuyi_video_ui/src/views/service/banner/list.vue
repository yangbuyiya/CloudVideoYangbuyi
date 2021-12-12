<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div class="app-container">
    <!--表单查询-->
    <el-form :inline="true" class="demo-form-inline">
      <!--标题-->
      <el-form-item label="标题">
        <el-input v-model="query.title" placeholder="标题" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getBannerList()">查询</el-button>
        <el-button type="default" @click="resetData()">清空</el-button>
      </el-form-item>
    </el-form>
    <div style="margin: 10px;">
      <el-button type="primary" @click="drawer = true;resetData();title='新增焦点图';">新增</el-button>
    </div>
    <el-card>
      <!--绑定表格组件-->
      <el-table :data="list" border fit highlight-current-row>

        <el-table-column label="序号" width="70" align="center">
          <template slot-scope="scope">  <!--通过slot拿到表格当中绑定的数据-->
            {{ scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column label="标题" prop="title" align="center" />
        <el-table-column label="图片地址" prop="imageUrl" width="500px" align="center">
          <template slot-scope="scope">
            <img :src="scope.row.imageUrl" style="width: 450px; height: 150px" alt="背景图片">
          </template>
        </el-table-column>
        <el-table-column label="链接地址" prop="linkUrl" align="center">
          <template slot-scope="scope">
            <a :href="scope.row.linkUrl" target="_blank" style="text-decoration: none">
              {{ scope.row.linkUrl }}
            </a>
          </template>
        </el-table-column>
        <el-table-column label="背景颜色" prop="imageBg" align="center">
          <template slot-scope="scope">
            <color-picker :value="scope.row.imageBg" />
            <div>
              {{ scope.row.imageBg }}
            </div>
          </template>
        </el-table-column>
        <el-table-column label="排序" prop="sort" width="60" align="center" />
        <el-table-column label="操作" width="200" align="center">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" icon="el-icon-edit" @click="updateBanner(scope.row.id)">修改</el-button>
            <el-button
              type="danger"
              size="mini"
              icon="el-icon-delete"
              @click="deleteBannerWithId(scope.row.id)"
            >删除
            </el-button>
          </template>

        </el-table-column>

      </el-table>
      <!-- 分页组件开始 -->
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="query.page"
        :limit.sync="query.limit"
        @pagination="getBannerList"
      />
      <!-- 分页组件结束 -->
    </el-card>

    <!--   添加or修改  -->
    <el-drawer
      :title="title"
      :visible.sync="drawer"
      :direction="direction"
    >
      <div class="drawer-div">
        <el-form ref="form" :inline="false" :model="form" :rules="rules" class="demo-form-inline" label-width="90px">
          <el-form-item label="标题" prop="title">
            <el-input v-model="form.title" placeholder="标题" />
          </el-form-item>
          <el-form-item label="背景颜色">
            <color-picker v-model="form.imageBg" style="z-index: 9999999 !important;" :change="headerChangeColo" />
          </el-form-item>
          <el-form-item label="焦点图" prop="imageUrl">
            <el-upload
              :show-file-list="false"
              :action="uploadPath"
              :on-success="uploadSuccess"
              :limit="1"
              name="file"
            >
              <div style="width: 450px;height: 150px;border: 1px solid #bebebe;">
                <img v-if="form.imageUrl" :src="form.imageUrl" style="width: 450px; height: 150px" alt="背景图片">
              </div>
            </el-upload>
          </el-form-item>
          <el-form-item label="链接地址" prop="linkUrl">
            <el-input v-model="form.linkUrl" placeholder="链接地址" />
          </el-form-item>
          <el-form-item label="排序">
            <el-input-number v-model="form.sort" label="排序" @change="handleChangeNumber" />
          </el-form-item>
          <el-form-item label-width="80%">
            <el-button type="primary" @click="handlerSubmit()">提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-drawer>
  </div>

</template>

<script>

import Api from '@/api/cms/banner'
import Pagination from '@/components/Pagination/index'

export default {
  name: 'Index',
  components: { Pagination },
  data() {
    return {
      color: '',
      title: '添加焦点图',
      drawer: false,
      direction: 'rtl',
      query: {
        page: 1,
        limit: 10,
        title: ''
      },
      // 文件上传地址
      uploadPath: process.env.VUE_APP_BASE_UPLOAD,
      form: {
        id: '',
        title: '',
        imageBg: '',
        imageUrl: 'https://oss-yby.yangbuyi.top/2021/10/28/f34c96d4eac241c7882968a71d9878663.png', // 封面
        linkUrl: 'https://yangbuyi.top',
        sort: 1
      },
      total: 0,
      list: [],
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' }
        ],
        imageUrl: [
          { required: true, message: '请上传背景图片', trigger: 'blur' }
        ],
        linkUrl: [
          { required: true, message: '请输入图片链接', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.getBannerList()
  },
  methods: {
    headerChangeColo(val) {
      this.color = val
    },
    handleChangeNumber(val) {
      this.form.sort = val
    },
    // 上传成功的回调
    uploadSuccess(res, file) {
      // https://oss-yby.yangbuyi.top/2021/10/28/f34c96d4eac241c7882968a71d9878663.png
      this.form.imageUrl = res.data.rows
    },
    // 获取焦点列表
    getBannerList() {
      Api.getBannerPageList(this.query).then(res => {
        this.list = res.data.items
        this.total = res.data.total
      }).catch(onerror => {
        console.log(onerror)
      })
    },
    // 提交表单
    handlerSubmit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.id && this.form.id !== '') {
            Api.updateBanner(this.form).then(res => {
              if (res.success) {
                this.msgSuccess(res.message)
                this.drawer = false
                this.getBannerList()
              } else {
                this.msgError(res.message)
              }
            })
          } else {
            Api.addBanner(this.form).then(res => {
              if (res.success) {
                this.msgSuccess(res.message)
                this.drawer = false
                this.getBannerList()
              } else {
                this.msgError(res.message)
              }
            })
          }
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    updateBanner(id) {
      this.title = '修改焦点图'
      this.drawer = true
      Api.getBannerById(id).then(res => {
        this.form = res.data.item
      })
    },
    // 删除创作者
    deleteBannerWithId(id) {
      // 用户提示
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 调用删除的api
        Api.removeBanner(id).then(response => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          // 重新加载数据
          this.getBannerList()
        })
      })
    },
    resetData() { /* 清空查询的数据*/
      this.query = { // 清空表单
        page: 1,
        limit: 10,
        title: ''
      }
      this.form = {
        id: '',
        title: '',
        imageBg: '',
        imageUrl: 'https://oss-yby.yangbuyi.top/2021/10/28/f34c96d4eac241c7882968a71d9878663.png', // 封面
        linkUrl: 'https://yangbuyi.top',
        sort: 1
      }
      // 重新查询
      this.getBannerList()
    }
  }
}
</script>

<style scoped>

</style>
