<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="作者名称">
        <el-input v-model="author.name" />
      </el-form-item>

      <el-form-item label="排序">
        <el-input-number v-model="author.sort" :min="0" controls-position="right" />
      </el-form-item>

      <el-form-item label="级别">
        <el-select v-model="author.level" clearable placeholder="选择级别">
          <el-option label="特约创作者" :value="0" />
          <el-option label="普通创作者" :value="1" />
        </el-select>
      </el-form-item>

      <el-form-item label="工作经历">
        <el-input v-model="author.career" />
      </el-form-item>

      <el-form-item label="上传头像">
        <pan-thumb :width="String('100px')" :height="String('100px')" :image="String(author.avatar)" />
        <el-button type="primary" @click="imageCropperShow=true">上传头像</el-button>
        <!--上传头像的界面-->
        <image-cropper
          v-show="imageCropperShow"
          :key="cropperKey"
          :width="500"
          :height="300"
          :url="BASE_API"
          field="file"
          @close="close"
          @crop-upload-success="cropSuccess"
        />

      </el-form-item>

      <el-form-item label="创作者简介">
        <el-input v-model="author.intro" type="textarea" :rows="10" />
      </el-form-item>

      <el-form-item>
        <el-button :disabled="false" type="primary" @click="saveClick()">保存</el-button>
      </el-form-item>

    </el-form>

  </div>
</template>

<script>
/* 引入author.js 调用对应的请求方法*/
import authorApi from '@/api/video/author'
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'

export default {
  components: { ImageCropper, PanThumb },
  data() {
    return {
      author: {
        sort: 0,
        level: 1,
        name: ''
      },
      // 图片显示
      imageCropperShow: false,
      // 上传key
      cropperKey: 0,
      // 文件上传地址
      BASE_API: process.env.VUE_APP_BASE_UPLOAD
    }
  },
  created() { // 一进来就要获取参数
    // 判断有没有参数 ，如果有， 获取参数
    if (this.$route.params && this.$route.params.id) {
      const id = this.$route.params.id
      // 根据id获取该条参数对应的数据  --> 发送请求到服务器当中获取数据
      authorApi.getOneAuthor(id).then(res => {
        this.author = res.data.author
      })
    } else {
      this.author = {
        sort: 0,
        level: 1
      }
    }
  },
  methods: {
    saveClick() {
      // 根据有没有id判断 是添加 还是更新
      // 如果是有id  那么就是更新 否则就是添加
      if (this.author.id) {
        // 更新
        this.updateAuthor()
      } else {
        // 保存添加
        this.addAuthor()
      }
    },
    // 添加
    addAuthor() {
      // 调用接口，做添加操作
      authorApi.addAuthor(this.author).then(() => {
        // 添加成功
        this.$message({
          type: 'success',
          message: '添加成功!'
        })
        // 跳转到列表页
        this.$router.push({ path: '/author/table' })
      })
    },
    updateAuthor() {
      // 调用更新的api
      authorApi.updateAuthor(this.author).then(() => {
        // 添加成功
        this.$message({
          type: 'success',
          message: '修改成功!'
        })
        // 跳转到列表页
        this.$router.push({ path: '/author/table' })
      })
    },
    close() {
      // 关闭弹框
      this.imageCropperShow = false
      this.cropperKey = this.cropperKey + 1
    },
    /**
     * 上传成功的回调
     * @param data
     */
    cropSuccess(data) {
      // 关闭弹框
      this.imageCropperShow = false
      // 显示图片
      this.author.avatar = data.rows
      this.cropperKey = this.cropperKey + 1
    }
  }
}
</script>

<style scoped>

</style>
