<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div>
    <!-- 阿里云视频播放器样式 -->
    <link rel="stylesheet" href="https://g.alicdn.com/de/prismplayer/2.8.1/skins/default/aliplayer-min.css">
    <!-- 阿里云视频播放器脚本 -->
    <script charset="utf-8" type="text/javascript" src="https://g.alicdn.com/de/prismplayer/2.8.1/aliplayer-min.js"/>


    <!--播放器页面布局-->
    <div class="video_content"> <!--左侧播放器-->
      <div class="back_button">
        <!--视频上方返回链接 video/_id.vue-->
        <a :href="'/video/'+contentId">
          <i class="el-icon-back" style="font-size: 25px; color: white; position: absolute; left: 12px; top: 28px;"/>|
          <span>{{ content.title }}</span>
        </a>
      </div>
      <!--播放器信息-->
      <div id="J_prismPlayer" class="prism-player"/>
    </div>

    <!--右侧目录-->
    <div class="catalogue">
      <!--顶部作品信息-->
      <div class="videoPlay_right_top">
        <img :src="content.cover" alt="图片">
        <span>
          <p class="dan">{{ content.title }}</p>
          <span style="color:white">购买人数：</span>
            <i class="pinfen_num">{{ content.buyCount }}</i>
         </span>
        <div class="clearfix"></div>
      </div>
      <div class="line"></div>
      <div class="m-chapterList">
        <div v-for="(chapter,index) in chapterList" :key="chapter.id">
          <div class="chapter">
            <span class="f-fl ch">章节</span>
            <span class="f-fl f-thide chaptericon"><span>{{ index + 1 }}</span></span>
            <span class="f-fl f-thide chaptername">{{ chapter.title }}</span>
          </div>
          <div class="section"
               :class="video.videoSourceId == vid?'video_active':''"
               v-for="video in chapter.children" :key="video.id"
               @click="videoClick(video.videoSourceId,video.isFree)">
            <span class="f-fl f-thide ks" style="color: rgb(153, 153, 153);">&nbsp;</span>
            <span class="f-fl ksw"
                  :style="video.videoSourceId == vid ? 'background: #f15858;':'background: rgb(245, 247, 250);'">
                <span class="u-clicon ksicon ksicon-20" title="进行中"></span>
            </span>
            <span class="f-fl ksname" style="color: rgb(153, 153, 153);">{{ video.title }}</span>
            <span class="f-fr ksinfo"><span class="f-fr ksinfoicon ksinfoicon-2" title="视频"></span>
                <span class="f-fr kstime">{{ video.duration }}</span><span class="f-fr flag flag-10"></span>
              </span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import vod from '@/api/vod'
import content from "@/api/content";
import "~/assets/css/video.css";

export default {
  layout: 'video',//应用video布局
  //加载数据
  async asyncData(app) {
    /**传入了两个参数,使用_分隔 拆分参数   vid为当前文件的名称_vid*/
    var arr = app.params.vid.split("_");
    var vid = arr[0]
    var contentId = arr[1];
    let [request1Data, request2Data] = await Promise.all([
      //获取播放凭证
      vod.getPlayAuth(vid),
      //获取作品详情
      content.geContentDetailById(contentId, app)
    ])
    return {
      vid: vid, // url
      contentId: contentId,
      playAuth: request1Data.data.playAuth,
      content: request2Data.data.content,
      chapterList: request2Data.data.chapterVoList,
      isBuy: request2Data.data.isBuyContent
    }
  },
  data() {
    return {
      contentId: '',
      aliPlayer: '',
      isBuy: false
    }
  },
  methods: {
    // 监听视频-获取视频凭证
    videoClick(vid, isFree) {
      console.log(this.isBuy);
      if (!this.isBuy) {
        this.$message.error("请进行购买付费课程!")
        return;
      }
      this.vid = vid;
      vod.getPlayAuth(vid).then(response => {
        this.playAuth = response.data.playAuth;
        // 传递视频资源id 与 视频播放凭证信息
        this.aliPlayer.replayByVidAndPlayAuth(vid, this.playAuth);
      })
    }
  },
  mounted() {
    setTimeout(() => {
      //页面渲染之后
      this.aliPlayer = new Aliplayer({
        id: 'J_prismPlayer',
        vid: this.vid, // 视频id
        playauth: this.playAuth, // 播放凭证
        encryptType: '1', // 如果播放加密视频，则需设置encryptType=1，非加密视频无需设置此项
        width: '1500px',
        height: '820px',
        // 以下可选设置
        qualitySort: 'asc', // 清晰度排序
        mediaType: 'video', // 返回音频还是视频
        autoplay: false, // 自动播放
        isLive: false, // 直播
        rePlay: false, // 循环播放
        preload: true,
        controlBarVisibility: 'hover', // 控制条的显示方式：鼠标悬停
        useH5Prism: true // 播放器类型：html5
      }, function (player) {
        console.log('播放器创建成功')

      })
    }, 0)
    /*this.videoClick(this.vid);*/
  }
}
</script>
<style>
html, body {
  height: 100%;
  padding-left: 20px;
  box-sizing: border-box;
  background: #1A2633;
}

.section_a:hover {
  background: gray;
}

.back_button {
  height: 80px;
  line-height: 80px;
  font-size: 18px;
  color: white;
  padding-left: 25px;
}

.back_button a {
  color: white;
  text-decoration: none;
}

.prism-player video {
  width: 100% !important;
  height: 100% !important;
}

.video_content {
  float: left;
  width: 80%;

}

.prism-cover {
  background: no-repeat !important;
}

.catalogue {
  float: left;
  width: 19%;
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  overflow: auto;
  background: white;
  margin-left: 10px;
}

.videoPlay_right_top {
  width: auto;
  height: auto;
  padding: 30px 20px;
  background: #545E86;
  font-size: 15px;
  color: white;
}

.videoPlay_right_top img {
  float: left;
  width: 70px;
  height: 40px;
  vertical-align: middle;
  background: #FFFFFF;
  border-radius: 5px;
  margin-right: 10px;
}

.m-chapterList .chapter {
  height: 40px;
  color: #666;
  font-size: 14px;
  font-family: '微软雅黑';
  font-weight: bolder;
  padding: 10px 12px 0 12px;
  line-height: 40px;
  cursor: default;
}

.m-chapterList .ch {
  width: 41px;
  height: 40px;
  text-align: left;
}

.m-chapterList .chaptericon {
  height: 25px;
  width: 25px;
  margin: 6px;
  text-align: center;
  line-height: 30px;
  font-size: 14px;
  border-radius: 12.5px;
  background: white;
  border: 1px solid #ccc;
}

.m-chapterList .chaptername {
  width: 185px;
  padding-left: 6px;
}

.m-chapterList .chaptericon span {
  display: block;
  height: 25px;
  width: 25px;
  font-size: 14px;
  margin-top: -1px;
}

.m-chapterList .section {
  height: 36px;
  line-height: 36px;
  padding: 0 12px;
  cursor: pointer;
  transition: all 0.15s ease-out;
}

.m-chapterList .video_active {
  background: #545E86 !important;
}

.m-chapterList .section:hover {
  background: #545E86;
}

.m-chapterList .section:hover .ksname {
  color: white !important;
}

.m-chapterList .video_active .ksname {
  color: white !important;
}

.m-chapterList .video_active .kstime {
  color: white !important;
}

.m-chapterList .section .ks {
  width: 51px;
  text-align: left;
  font-size: 12px;
}

.m-chapterList .section:hover .ks {
  color: white !important;
}

.m-chapterList .section .ksw {
  height: 14px;
  width: 14px;
  margin: 11px 0 0 0;
  transition: all 0.15s ease-out;
  background: white;
  border-radius: 7px;
  border: 1px solid #ccc;
}

.m-chapterList .section .ksname {
  padding-left: 8px;
  color: #999;
  max-width: 123px;
  height: 36px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.m-chapterList .section .ksinfo {
  height: 36px;
}

.m-chapterList .section .kstime {
  color: #bababa;
}

.m-chapterList .section:hover .kstime {
  color: white;
}

.m-chapterList .section .flag {
  width: 16px;
  height: 18px;
  margin: 12px 3px 0 3px;
}

.line {
  width: 1px;
  position: absolute;
  top: 100px;
  left: 71px;
  bottom: 0px;
  background: #bababa;
}

</style>
<style>
.vip-join {
  color: #00c1de;
}

.vip_limit_content {
  display: flex;
  width: 100%;
  height: 100%;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}

.vip_limit_content .title {
  font-size: 18px;
  line-height: 36px;
  color: #fff;
  text-align: center;
  width: 100%;
}

.vip_limit_button_box {
  text-align: center;
  white-space: nowrap;
  overflow: hidden;
  width: 100%;
}

.vip_limit_btn {
  display: inline-block;
  min-width: 100px;
  position: relative;
  background: #f60;
  padding: 0 35px;
  margin: 0px 5px 20px 5px;
  border-radius: 38px;
  font-size: 18px;
  line-height: 38px;
  color: #623A0C;
  text-align: center;
  background-image: linear-gradient(-135deg, #FBE8A8 0%, #F8E7AC 15%, #E2C078 100%);
  cursor: pointer;
}

.vip_limit_close {
  text-align: center;
}

.vip_limit_close span {
  display: inline-block;
  width: 40px;
  height: 40px;
  line-height: 36px;
  background: rgba(165, 165, 165, 0.54);
  border-radius: 50%;
  font-size: 24px;
  cursor: pointer;
}
</style>
