<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div class="app-container">
    <!--表单查询-->
    <el-form :inline="true" class="demo-form-inline">
      <!--姓名-->
      <el-form-item label="姓名">
        <el-input v-model="query.name" placeholder="姓名" />
      </el-form-item>
      <!--下拉列表-->
      <el-form-item label="级别">
        <el-select v-model="query.level" clearable placeholder="选择级别">
          <el-option label="特约创作者" value="0" />
          <el-option label="普通创作者" value="1" />
        </el-select>
      </el-form-item>
      <!--日期选择-->
      <el-form-item>
        <el-date-picker
          v-model="query.begin"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00：00：00"
          placeholder="选择开始时间"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="query.end"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00：00：00"
          placeholder="选择截止时间"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="getAuthorList()">查询</el-button>
        <el-button type="default" @click="resetData()">清空</el-button>
      </el-form-item>

    </el-form>

    <!--绑定表格组件-->
    <el-table :data="list" border fit highlight-current-row>

      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">  <!--通过slot拿到表格当中绑定的数据-->
          {{ scope.$index+1 }}
        </template>
      </el-table-column>
      <el-table-column label="姓名" prop="name" width="100" align="center" />
      <el-table-column label="级别" prop="level" width="100">
        <template slot-scope="scope">  <!--通过slot拿到表格当中绑定的数据-->
          {{ scope.row.level === 1 ? "普通创作者" : "特约创作者" }}
        </template>
      </el-table-column>
      <el-table-column label="创作者简介" prop="intro" />
      <el-table-column label="添加时间" prop="gmtCreate" width="160" />
      <el-table-column label="排序" prop="sort" width="60" />
      <el-table-column label="操作" width="200" align="center">

        <template slot-scope="scope">
          <!--修改的跳转  让其跳转到路由当中 编辑路由 页面和添加的是一样， 编辑当中是有参数
             在定义一个编辑的路由
         -->
          <router-link :to="'/author/authorEdit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
          </router-link>

          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="deleteAuthorWithId(scope.row.id)"
          >删除</el-button>
        </template>

      </el-table-column>

    </el-table>
    <!-- 分页组件开始 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="query.page"
      :limit.sync="query.limit"
      @pagination="getAuthorList"
    />
    <!-- 分页组件结束 -->
  </div>
</template>

<script>

import Api from '@/api/video/author'
import Pagination from '@/components/Pagination/index'

export default {
  name: 'List',
  components: { Pagination },
  data() {
    return {
      query: {
        page: 1,
        limit: 10,
        name: '',
        level: '',
        begin: '',
        end: ''
      },
      total: 0,
      list: []
    }
  },
  created() {
    this.getAuthorList()
  },
  methods: {
    getAuthorList() {
      Api.getAuthorPageList(this.query).then(res => {
        this.list = res.data.rows
        this.total = res.data.total
      }).catch(onerror => {
        console.log(onerror)
      })
    },
    // 删除创作者
    deleteAuthorWithId(id) {
      // 用户提示
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 调用删除的api
        Api.deleteAuthorWithId(id).then(response => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          // 重新加载数据
          this.getAuthorList()
        })
      })
    },
    resetData() { /* 清空查询的数据*/
      this.query = { // 清空表单
        page: 1,
        limit: 10,
        name: '',
        level: '',
        begin: '',
        end: ''
      }
      // 重新查询
      this.getAuthorList()
    }
  }
}
</script>

<style scoped>

</style>
