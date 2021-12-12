<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div class="app-container">
    <el-form label-width="120px">
      <!--下载excel模板-->
      <el-form-item label="描述信息">
        <el-tooltip class="item" effect="dark" :content="content" placement="top-start">
          <el-tag type="info">excel模板说明</el-tag>
        </el-tooltip>
        <el-tag>
          <i class="el-icon-download"/>
          <a :href="BASE_API+'/service_upload/file/downloadTemp'" target="_blank">点击下载模板</a>
        </el-tag>
      </el-form-item>
      <!--上传excel-->
      <el-form-item label="上传信息">
        <el-upload
          ref="upload"
          :action="BASE_API+'/service_video/category/addCategory'"
          :auto-upload="false"
          :on-success="uploadSuccess"
          :on-error="uploadError"
          :on-change="handFileChange"
          accept=".xls, .xlsx"
          :limit="1"
          :disabled="btnDisabled"
          :file-list="fileList"
          name="file"
        >
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <el-button
            :loading="loading"
            style="margin-left: 10px;"
            size="small"
            type="success"
            @click="submitUpload"
          >上传到服务器
          </el-button>
        </el-upload>
      </el-form-item>
    </el-form>
  </div>

</template>

<script>
export default {
  name: 'Save',
  data() {
    return {
      btnDisabled: false,
      loading: false,
      BASE_API: process.env.VUE_APP_BASE_API,
      content: '',
      fileList: []
    }
  },
  created() {
    this.content = '请严格按照模板来进行填写数据格式,列如 科技:-> 洗衣机、电脑'
  },
  methods: {
    handFileChange(files, fileList) {
      this.fileList.push(files)
    },
    submitUpload() {
      console.log(this.fileList)
      if (this.fileList.length <= 0) {
        this.$message({
          type: 'error',
          message: '请选择文件进行上传'
        })
        return
      }
      this.$refs.upload.submit()
      this.btnDisabled = true
      this.loading = true
    },
    uploadSuccess() {
      this.loading = false
      this.$message({
        type: 'success',
        message: '添加作品分类成功'
      })
      /* 跳转到列表页*/
      this.$router.push({ path: '/category/categoryList' })
    },
    uploadError() {
      alert('error')
    }
  }

}
</script>

<style scoped>

</style>
