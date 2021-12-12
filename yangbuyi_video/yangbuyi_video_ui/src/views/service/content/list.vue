<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>

  <div class="app-container">

    <el-card style="margin-bottom: 10px;">
      <!--查询表单-->
      <el-form :inline="true" class="demo-form-inline">
        <!-- 所属分类：级联下拉列表 -->
        <!-- 一级分类 -->
        <el-form-item label="分类">
          <el-select
            v-model="query.categoryParentId"
            placeholder="请选择"
            @change="oneCategoryChanged"
          >
            <el-option
              v-for="category in oneCategoryList"
              :key="category.id"
              :label="category.title"
              :value="category.id"
            />
          </el-select>
          &nbsp;
          <!-- 二级分类 -->
          <el-select v-model="query.categoryId" placeholder="请选择">
            <el-option
              v-for="twoCategory in twoCategoryList"
              :key="twoCategory.id"
              :label="twoCategory.title"
              :value="twoCategory.id"
            />
          </el-select>

        </el-form-item>

        <!-- 标题 -->
        <el-form-item>
          <el-input v-model="query.title" placeholder="作品标题" />
        </el-form-item>
        <!-- 作者 -->
        <el-form-item>
          <el-select
            v-model="query.authorId"
            placeholder="请选择作者"
          >
            <el-option
              v-for="author in authorList"
              :key="author.id"
              :label="author.name"
              :value="author.id"
            />
          </el-select>
        </el-form-item>

        <el-button type="primary" icon="el-icon-search" @click="getContentListPage()">查询</el-button>
        <el-button type="default" @click="resetData()">清空</el-button>
      </el-form>
    </el-card>

    <el-card style="padding: 20px;">
      <el-row :gutter="15" style="width: 1200px;margin: 0 auto;">
        <el-col v-for="item in list" :key="item.id" :span="6">
          <div class="grid-content bg-purple">
            <img :src="item.cover" :alt="item.cover" width="100%" height="150px">
            <a href="#" style="font-size: 14px; color: #333">{{ item.title }}</a>
            <p style="margin-top: 0;">
              <router-link :to="'/content/info/'+item.id">
                <el-button type="text" size="mini" icon="el-icon-edit">编辑作品信息</el-button>
              </router-link>
              <el-button
                style="margin-left: 100px;"
                type="text"
                size="mini"
                icon="el-icon-delete"
                @click="deleteContentById(item.id)"
              >删除
              </el-button>
            </p>
            <p style="font-size: 14px; color: red; margin-top: -15px;">
              价格: {{ Number(item.price) === 0 ? '免费' : '¥' + item.price.toFixed(2) }}
            </p>
          </div>
        </el-col>
      </el-row>
      <!-- 分页 -->
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="query.page"
        :limit.sync="query.limit"
        @pagination="getContentListPage"
      />
    </el-card>
  </div>

</template>

<script>
import content from '@/api/video/content'
import category from '@/api/video/category'
import api from '@/api/video/content'

export default {
  name: 'List',
  data() {
    return {
      listLoading: true, // 是否显示loading信息
      list: null, // 数据列表
      total: 0, // 总记录数
      query: {
        categoryParentId: '',
        categoryId: '',
        title: '',
        authorId: '',
        page: 1, // 页码
        limit: 10 // 每页记录数
      }, // 查询条件
      authorList: [], // 作者列表
      oneCategoryList: [], // 一级分类列表
      twoCategoryList: [] // 二级分类列表,
    }
  },
  mounted() {
    this.getCategory()
    this.getAuthorList()
    this.getContentListPage()
  },
  methods: {
    getContentListPage() {
      content.getContentListPage(this.query).then(res => {
        this.list = res.data.rows
        this.total = res.data.total
      })
    },
    deleteContentById(id) {
      // debugger
      this.$confirm('真的的要删除该作品?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return content.deleteContentById(id)
      }).then(() => {
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
        this.getContentListPage()
      }).catch((response) => { // 失败
        if (response === 'cancel') {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        }
      })
    },
    // 加载作者下拉
    getAuthorList() {
      api.getAuthorList().then(res => {
        this.authorList = res.data.rows
      }).catch(onerror => {
        console.log(onerror)
      })
    },
    // 加载分类
    getCategory() {
      // 一进入页面加载列表数据
      category.getCategoryData().then(res => {
        this.oneCategoryList = res.data.rows
      })
    },
    // 监听一级分类
    oneCategoryChanged(val) {
      const data = this.oneCategoryList.filter(e => e.id === val)
      this.twoCategoryList = data[0].children
    },
    resetData() {
      this.query = {
        categoryParentId: '',
        categoryId: '',
        title: '',
        authorId: '',
        page: 1, // 页码
        limit: 10 // 每页记录数
      }
      this.twoCategoryList = [] // 二级分类列表
      this.getContentListPage()
    }
  }
}
</script>

<style scoped>
.app-container {
  /*width: 1260px;*/
  /*margin: 0 auto;*/
}

.el-col {
  margin-top: 10px;
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
  height: 250px;
  transition: .3s all linear;
  padding: 10px;
  box-sizing: border-box;
}
.grid-content:hover {
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10% );
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

</style>
