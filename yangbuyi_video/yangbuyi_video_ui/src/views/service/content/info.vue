<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div class="app-container">
    <h1 style="text-align: center">添加作品</h1>
    <el-steps :active="1" finish-status="success">
      <el-step title="填写作品基本信息" />
      <el-step title="添加章节视频" />
      <el-step title="完成" />
    </el-steps>

    <el-form label-width="120px" style="margin-top: 10px">
      <!--标题-->
      <el-form-item label="作品标题">
        <el-input v-model="contentVO.title" placeholder="请填写作品标题" />
      </el-form-item>
      <!--分类 -->
      <el-form-item label="作品分类">
        <el-select v-model="contentVO.categoryParentId" filterable placeholder="一级分类" @change="oneCategoryChanged">
          <el-option
            v-for="category in oneCategoryList"
            :key="category.id"
            :label="category.title"
            :value="category.id"
          />
        </el-select>
        &nbsp;
        <el-select v-model="contentVO.categoryId" filterable placeholder="二级分类">
          <el-option
            v-for="category in twoCategoryList"
            :key="category.id"
            :label="category.title"
            :value="category.id"
          />
        </el-select>
      </el-form-item>
      <!--作者-->
      <el-form-item label="作者">
        <el-select v-model="contentVO.authorId" filterable placeholder="选择作者">
          <el-option
            v-for="author in authorList"
            :key="author.id"
            :label="author.name"
            :value="author.id"
          />
        </el-select>
      </el-form-item>
      <!--总视频数-->
      <el-form-item label="总视频数">
        <el-input v-model="contentVO.contentNum" :min="0" placeholder="总视频数" />
      </el-form-item>
      <!--简介-->
      <el-form-item label="作品简介">
        <tinymce ref="editor" v-model="contentVO.description" />
      </el-form-item>
      <!--封面-->
      <el-form-item label="作品封面">

        <el-upload
          :show-file-list="false"
          :action="uploadPath"
          :on-success="uploadSuccess"
          :limit="1"
          name="file"
        >
          <img :src="contentVO.cover" style="width: 300px; height: 150px" alt="默认图">
        </el-upload>

      </el-form-item>

      <!--价格-->
      <el-form-item label="价格">
        <el-input v-model="contentVO.price" :min="0" placeholder="价格" />
      </el-form-item>

      <el-button style="margin-top: 12px;" type="primary" @click="saveAndNext">保存并下一步</el-button>

    </el-form>

  </div>

</template>

<script>

import api from '@/api/video/content'
import category from '@/api/video/category'
import tinymce from '@/components/Tinymce'

export default {
  components: {
    tinymce
  },
  data() {
    return {
      active: 0,
      contentVO: {
        title: '', // 标题
        categoryId: '', // 二级分类ID
        categoryParentId: '', // 父级分类ID
        authorId: '', // 作者id
        contentNum: '', // 总视频数
        description: '', // 简介
        cover: require('@/assets/cover.jpg'), // 封面
        price: 0 // 金额 0则为免费课程
      },
      authorList: [],
      oneCategoryList: [],
      twoCategoryList: [],
      // 文件上传地址
      uploadPath: process.env.VUE_APP_BASE_UPLOAD
    }
  },
  mounted() {
    this.getCategory()
    this.getAuthorList()

    if (this.$route.params && this.$route.params.id && this.$route.params.id !== ':id') {
      this.contentId = this.$route.params.id
      this.getContentInfo()
    }
  },
  methods: {
    saveAndNext() {
      if (this.contentId && this.contentId !== ':id') {
        api.updateContentInfo(this.contentVO).then(res => {
          this.toMsg(res)
          this.$router.push({ path: '/content/chapter/' + this.contentId })
        })
      } else {
        api.addContentInfo(this.contentVO).then(res => {
          this.msgSuccess('添加成功')
          this.$router.push({ path: '/content/chapter/' + res.data.contentId })
        })
      }
    },
    getContentInfo() {
      api.getContentWhitInfo(this.contentId).then(res => {
        this.oneCategoryChanged(res.data.rows.categoryParentId)
        this.contentVO = res.data.rows
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
      this.contentVO.categoryId = ''
      const data = this.oneCategoryList.filter(e => e.id === val)
      this.twoCategoryList = data[0].children
    },
    // 上传成功的回调
    uploadSuccess(res, file) {
      this.contentVO.cover = res.data.rows
    }

  }
}
</script>

<style scoped>

</style>
