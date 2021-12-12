<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div class="app-container">
    <el-steps :active="2" finish-status="success">
      <el-step title="填写作品基本信息" />
      <el-step title="添加章节视频" />
      <el-step title="完成" />
    </el-steps>

    <el-button type="text" @click="dialogChapterFormVisible = true;chapter = {};getContentOrVideoSortMax(1)">添加章节
    </el-button>
    <!-- 章节 -->
    <ul class="chapterList">
      <li v-for="c in chapterNestedList" :key="c.id">
        <p>
          {{ c.title }}
          <span class="acts">
            <el-button
              type="text"
              @click="dialogVideoFormVisible = true;chapterId = c.id;contentVideo = {};fileList = [];getContentOrVideoSortMax(2)"
            >添加小节</el-button>
            <el-button
              style=""
              type="text"
              @click="dialogChapterFormVisible = true;chapter=c;getContentOrVideoSortMax(1)"
            >编辑</el-button>
            <el-button type="text" @click="deleteChapter(c)">删除</el-button>
          </span>
        </p>
        <!-- 视频 -->
        <ul class="chapterListList sectionList">
          <li v-for="section in c.children" :key="section.id">
            <p>
              {{ section.title }}
              <span class="acts">
                <el-button type="text" @click="editContentVideo(section.id);">编辑</el-button>
                <el-button type="text" @click="deleteContentVideo(section.id)">删除</el-button>
              </span>
            </p>
          </li>
        </ul>
      </li>
    </ul>
    <!-- 添加和修改章节表单 -->
    <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
      <el-form :model="chapter" label-width="120px">
        <el-form-item label="章节标题">
          <el-input v-model="chapter.title" />
        </el-form-item>
        <el-form-item label="章节排序">
          <el-input-number v-model="chapter.sort" :min="0" controls-position="right" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogChapterFormVisible = false;">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 添加和修改i小节表单 -->
    <el-dialog :visible.sync="dialogVideoFormVisible" title="添加小节" append-to-body="true" modal-append-to-body>
      <el-form :model="contentVideo" label-width="120px">
        <el-form-item label="小节标题">
          <el-input v-model="contentVideo.title" />
        </el-form-item>
        <el-form-item label="课时排序">
          <el-input-number v-model="contentVideo.sort" :min="0" controls-position="right" />
        </el-form-item>
        <el-form-item label="是否免费">
          <el-radio-group v-model="contentVideo.isFree">
            <el-radio :label="true">免费</el-radio>
            <el-radio :label="false">默认</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上传视频">
          <!-- 上传视频 -->
          <el-upload
            :on-success="handleVodUploadSuccess"
            :on-remove="handleVodRemove"
            :before-remove="beforeVodRemove"
            :before-upload="beforeVodUploadVideo"
            :on-exceed="handleUploadExceed"
            :file-list="fileList"
            :action="BASE_API"
            :limit="1"
            class="upload-demo"
          >
            <el-button size="small" type="primary">上传视频</el-button>
            <el-tooltip placement="right-end">
              <div slot="content">最大支持1G，<br>
                支持3GP、ASF、AVI、DAT、DV、FLV、F4V、<br>
                GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br>
                MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br>
                SWF、TS、VOB、WMV、WEBM 等视频格式上传
              </div>
              <i class="el-icon-question" />
            </el-tooltip>
          </el-upload>

        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdateVideo">确 定</el-button>
      </div>

    </el-dialog>

    <!-- 操作 -->
    <el-button style="margin-top: 12px;" @click="pre">上一步</el-button>
    <el-button style="margin-top: 12px;" @click="next">下一步</el-button>
  </div>

</template>

<script>
import api from '@/api/video/chapter'
import vod from '@/api/video/vod'
import contentVideoApi from '@/api/video/contentVideo'

export default {
  data() {
    return {
      active: 0,
      contentId: '',
      chapterNestedList: '',
      dialogChapterFormVisible: false, // 是否显示章节表单
      dialogVideoFormVisible: false, // 是否显示小节表单
      chapter: { // 章节对象
        title: '',
        sort: 0
      },
      chapterId: '', // 课时所在的章节id
      contentVideo: { // 课时对象
        title: '', // 标题
        sort: 0, // 排序
        isFree: 0, // 是否免费
        videoSourceId: '', // 视频资源
        videoOriginalName: '' // 原始名称
      },
      fileList: [], // 上传文件列表
      BASE_API: process.env.VUE_APP_BASE_UPLOAD_VOD
    }
  },
  created() {
    // 获取路由当中的id
    if (this.$route.params && this.$route.params.id) {
      // 获取当前作品id
      this.contentId = this.$route.params.id
      // 请求嵌套数据
      this.getNestedTreeList()
    }
  },
  methods: {
    /* 视频点播开始 */
    // 上传前校验视频
    beforeVodUploadVideo(file) {
      // 视频总时长
      const url = URL.createObjectURL(file)
      var audioElement = new Audio(url)
      audioElement.addEventListener('loadedmetadata', () => {
        // playTime就是当前视频长度
        this.viTimes = audioElement.duration
      })
      const is1024M = file.size / 1024 / 1024 < 1024
      if (['video/mp4','video/ogg', 'video/flv', 'video/avi', 'video/wmv', 'video/rmvb'].indexOf(file.type) === -1) { // 'video/ogg', 'video/flv', 'video/avi', 'video/wmv', 'video/rmvb'
        this.$message.error('请上传正确的视频格式')
        return false
      }
      if (!is1024M) {
        this.$message.error('上传视频大小不能超过1G!')
        return false
      }
    },
    // 自动上传成功回调
    handleVodUploadSuccess(response, file, fileList) {
      // 获取当前上传视频ID
      console.log(response)
      this.contentVideo.videoSourceId = response.data.videoId
      // 获取当前上传视频标题
      this.contentVideo.videoOriginalName = file.name
      // 设置文件名回显
      this.fileList = [{ 'name': this.contentVideo.videoOriginalName }]
      // 提示
      this.$message({
        type: 'success',
        message: '视频上传完毕!'
      })
    },
    // 已经超过了指定数量时, 调用此方法
    handleUploadExceed(files, fileList) {
      this.$message.warning('请先删除已上传的视频')
    },
    // 删除之前提示信息
    beforeVodRemove(file, fileList) {
      return this.$confirm(`确定删除 ${file.name}？`)
    },
    // 删除小节视频
    handleVodRemove(file, fileList) {
      if (this.contentVideo.videoSourceId != null || this.contentVideo.videoSourceId !== 'undefined') {
        vod.deleteVodById(this.contentVideo.videoSourceId).then(response => {
          // 清空当前小节视频id
          this.contentVideo.videoSourceId = ''
          // 清空当前小节视频标题
          this.contentVideo.videoOriginalName = ''
          this.fileList = []
          this.$message({
            type: 'success',
            message: response.message
          })
        })
      }
    },
    /* 视频点播结束 */
    saveOrUpdate() {
      if (!this.chapter.id) {
        this.saveData()
      } else {
        this.updateData()
      }
    },
    saveData() {
      this.chapter.contentId = this.contentId
      api.insertChapter(this.chapter).then(response => {
        this.$message({
          type: 'success',
          message: '保存成功!'
        })
        this.chapter.title = ''
        this.getNestedTreeList()
        this.dialogChapterFormVisible = false
      }).catch((response) => {
        this.$message({
          type: 'error',
          message: response.message
        })
      })
    },
    updateData() {
      api.updateChapter(this.chapter).then(response => {
        this.$message({
          type: 'success',
          message: '修改成功!'
        })
        this.getNestedTreeList()
        this.dialogChapterFormVisible = false
      }).catch((response) => {
        this.$message({
          type: 'error',
          message: response.message
        })
      })
    },
    getNestedTreeList() {
      api.getNestedTreeList(this.contentId).then(response => {
        this.chapterNestedList = response.data.rows
      })
    },
    deleteChapter(row) {
      api.deleteChapterById(row.id).then(response => {
        this.toMsg(response)
        this.getNestedTreeList()
      })
    },
    // 小节按钮点击
    saveOrUpdateVideo() {
      const sourceId = this.contentVideo.videoSourceId
      if (sourceId != null && sourceId !== '') {
        if (!this.contentVideo.id) {
          this.saveDataVideo()
        } else {
          this.updateDataVideo()
        }
      } else {
        this.$message({
          type: 'warning',
          message: '等待视频上传，请稍后...'
        })
      }
    },
    /* 保存小节数据信息*/
    saveDataVideo() {
      this.contentVideo.contentId = this.contentId
      this.contentVideo.chapterId = this.chapterId
      contentVideoApi.addContentVideo(this.contentVideo).then(response => {
        this.$message({
          type: 'success',
          message: '保存成功!'
        })
        this.rest()
      })
    },
    editContentVideo(id) {
      this.getContentOrVideoSortMax(2)
      contentVideoApi.getContentVideoById(id).then(res => {
        this.contentVideo = res.data.rows
        // 如果有视频, 显示视频标题
        if (this.contentVideo.videoOriginalName !== '') {
          this.fileList = [{ 'name': this.contentVideo.videoOriginalName }]
        }
        this.dialogVideoFormVisible = true
      })
    },
    updateDataVideo() {
      this.getContentOrVideoSortMax(2)
      contentVideoApi.updateContentVideo(this.contentVideo).then(response => {
        this.$message({
          type: 'success',
          message: '保存成功!'
        })
        this.rest()
      })
    },
    deleteContentVideo(videoId) {
      this.$confirm('永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return contentVideoApi.deleteContentVideoById(videoId)
      }).then(() => {
        this.getNestedTreeList()// 刷新列表
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
      }).catch((response) => { // 失败
        if (response === 'cancel') {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        }
      })
    },
    getContentOrVideoSortMax(type) {
      // api.getContentOrVideoSortMax(type).then(res => {
      //   if (type === 1) {
      //     this.chapter.sort = res.data.rows
      //   } else {
      //     this.contentVideo.sort = res.data.rows
      //   }
      // })
    },
    rest() {
      this.dialogChapterFormVisible = false
      this.dialogVideoFormVisible = false
      this.getNestedTreeList()
      this.contentVideo = { // 课时对象
        title: '', // 标题
        sort: 0, // 排序
        isFree: 0, // 是否免费
        videoSourceId: '' // 视频资源
      }
      // 清空当前小节视频标题
      this.contentVideo.videoOriginalName = ''
      this.fileList = []
    },
    pre() {
      this.$router.push({ path: '/content/info/' + this.contentId })
    },
    next() {
      // 保存章节
      this.$router.push({ path: '/content/send/' + this.contentId })
    }
  }
}
</script>

<style scoped>
ul {
  list-style: none;
}

.chapterList {
  position: relative;
  list-style: none;
  margin: 0;
  padding: 0;
}

.chapterList li {
  position: relative;
}

.chapterList p {
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #DDD;
}

.chapterList .acts {
  float: right;
  font-size: 14px;
}

.sectionList {
  padding-left: 50px;
}

.sectionList p {
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dashed #DDD;
}

</style>
