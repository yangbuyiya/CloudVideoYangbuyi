<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div id="videoList" class="bg-fa of">
    <section class="container">
      <section class="path-wrap txtOf hLh30">
        <a href="#" title class="c-999 fsize14">首页</a>
        \
        <a href="/course" title class="c-999 fsize14">作品列表</a>
        \
        <span class="c-333 fsize14">{{ content.oneCategoryName }}</span>
        \
        <span class="c-333 fsize14">{{ content.twoCategoryName }}</span>
      </section>
      <div>
        <div class="content-box">
          <div class="fl">
            <img width="450" height="250" :src="content.cover"/>
          </div>
          <div class="fl u-coursetitle">
            <h2>
              <span class="u-coursetitle_title">{{ content.title }}</span>
            </h2>
            <div style="margin-top: 10px; ">
              <div class="fl">{{ content.buyCount }}人学过</div>
              <div class="fl hot">
                <el-rate
                  v-model="value"
                  disabled
                  show-score
                  text-color="#ff9900"
                  score-template="{value}">
                </el-rate>
              </div>
              <div class="clear"></div>
            </div>
            <div v-if="Number(content.price) != 0" style="font-size: 28px; margin-top: 30px;">
              ¥ {{ content.price }}
            </div>
            <div class="join">
              <a @click="createOrders" class="learnbtn"
                 v-if="Number(content.price) != 0 && isBuy == false"><span>立即购买</span></a>
              <a href="#" class="j-do-trailer" @click="doStudy()"
                 v-if="Number(content.price) === 0 || isBuy== true"><span>立即学习</span>
              </a>
            </div>
          </div>
        </div>
        <div class="clear"></div>
      </div>
      <div class="mt20 c-infor-box">
        <article class="fl col-7">
          <section class="mr30">
            <el-tabs v-model="activeName" @tab-click="handleClick">
              <el-tab-pane style="padding-left: 20px" label="视频介绍" name="first">
                <div>
                  <h6 class="c-i-content c-infor-title">
                    <span>课程介绍</span>
                  </h6>
                  <div class="course-txt-body-wrap">
                    <section class="course-txt-body" style="padding: 20px;">
                      <div v-html="content.description"></div>
                    </section>
                  </div>
                </div>
              </el-tab-pane>
              <el-tab-pane label="视频目录" name="second">
                <div>
                  <div class="f-cb">
                    <h2 class="u-ctit f-thide f-fl">目录</h2>
                  </div>
                  <div class="m-chapterList f-pr">
                    <div class="chapter" v-for="(chapter,index) in chapterList" :key="chapter.id">
                      <div class="chapterhead">
                        <span class="f-fl f-thide chaptertitle">章节{{ index + 1 }}:</span>
                        <span class="f-fl f-thide chaptername">{{ chapter.title }}</span>
                      </div>
                      <div>
                        <div class="section" v-for="(video,i) in chapter.children" :key="video.id">
                          <!-- 跳转视频播放器 -->
                          <a href="#" @click="playClick(video.videoSourceId+'_'+contentId,video.isFree,$event)">
                            <span class="f-fl f-thide ks">小节{{ i + 1 }}</span><span class="f-fl type-title">视频</span>
                            <span class="f-fl f-thide ksname">{{ video.title }}</span>
                            <a class="f-fr ksjbtn j-hovershow" style="display: none;">
                              <span class="f-fr"
                                    v-if="video.isFree === true && (Number(content.price) === 0 || isBuy== true)">可试看</span>
                              <div class="f-fr ksjicon-look"></div>
                            </a>
                            <span class="f-fr ksinfo j-hoverhide">
                                <span class="f-fr f-thide kstime" style="color: white !important;">{{
                                    video.duration
                                  }}</span>
                                <span class="f-fr ksinfoicon ksinfoicon-2" title="视频"></span>
                                <span class="f-fr canPreview"
                                      v-if="video.isFree === true  && !(Number(content.price) === 0 || isBuy== true)">可试看</span>
                              </span>
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </el-tab-pane>
            </el-tabs>
          </section>
        </article>
        <aside class="fl col-3">
          <div class="i-box">
            <div>
              <section class="c-infor-tabTitle c-tab-title">
                <a title href="javascript:void(0)">作者</a>
              </section>
              <section class="stud-act-list">
                <ul style="height: auto;">
                  <li>
                    <div class="u-face">
                      <a :href="'/author/'+content.authorId">
                        <img :src="content.avatar" width="50" height="50" alt>
                      </a>
                    </div>
                    <section class="hLh30 txtOf">
                      <a class="c-333 fsize16 fl" href="#">{{ content.authorName }}</a>
                    </section>
                    <section class="hLh20 txtOf">
                      <span class="c-999">{{ content.intro }}</span>
                    </section>
                  </li>
                </ul>
              </section>
            </div>
          </div>
        </aside>
        <div class="clear"></div>
      </div>
    </section>
  </div>
</template>

<script>
import content from "@/api/content";
import order from "@/api/order";
import cookie from 'js-cookie';

export default {
  async asyncData(app) {
    //在nuxt中如果没有做任何处理, 是不能直接获取到cookie
    var content_id = app.params.id;  //node.js
    return content.geContentDetailById(content_id, app).then(response => {
      return {
        contentId: content_id,
        content: response.data.content,
        chapterList: response.data.chapterVoList,
        isBuy: response.data.isBuyContent
      }
    })
  },
  data() {
    return {
      content: '',
      chapterList: '',
      contentId: '',
      activeName: 'second',
      value: 4.5,
      isBuy: false,
      videoId: '',
    };
  },
  mounted() {
    this.initData()
  },
  methods: {
    initData() {
      // 初始化视频id 用于立刻学习
      const chapterList = this.chapterList;
      for (let i = 0; i < chapterList.length; i++) {
        const chapter = chapterList[i];
        for (const video of chapter.children) {
          console.log(video);
          this.videoId = video.videoSourceId + '_' + this.contentId
          return;
        }
        return;
      }
    },
    // 支付监听
    playClick(videoId, isFree, e) {
      if (isFree) {
        // nuxt 中 使用到了jquery
        $(e.currentTarget).attr('href', '/player/' + videoId)
        $(e.currentTarget).trigger('click');
      } else {
        // 有没有登录
        if (this.isLogin()) {
          // 是否购买课程
          if (this.isBuy) {
            $(e.currentTarget).attr('href', '/player/' + videoId)
            $(e.currentTarget).trigger('click');
          } else {
            this.$message.error("购买后才能观看")
          }
        } else {
          this.$message.warning("请先登录")
          setTimeout(() => {
            this.$router.push('/login')
          }, 2000)
        }
      }
    },
    // 立即学习
    doStudy() {
      // this.$router.push('/player/' + this.videoId)
      window.location.href = "/player/" + this.videoId;
    },
    handleClick(tab, event) {
      this.activeName = tab.paneName;
    },
    //生成订单
    async createOrders() {
      if (this.isLogin()) {
        order.createOrders(this.contentId).then(response => {
          //获取返回订单号
          //生成订单之后，跳转订单显示页面
          this.$router.push({path: '/order/' + response.data.rows})
        })
      } else {
        this.$message.warning("请先登录")
        setTimeout(() => {
          this.$router.push('/login')
        }, 2000)
      }
    },
    // 判断是否登陆
    isLogin() {
      let user = cookie.get('portal_user', {domain: 'localhost'});
      return !!user
    }

  }
}
</script>

<style scoped>

>>> .el-tabs__nav-scroll {
  background: white;
  height: 40px;
}

>>> .el-tabs__nav:first-child {
  margin-left: 15px;
}

>>> .el-tab-pane {
  background: white;
  border: 1px solid #f0f0f0;
}

>>> .el-tab-pane:first-child {
  padding-top: 20px;
}

>>> .el-rate__icon {
  margin-right: -2px;
}

a {
  text-decoration: none;
}

.m-chapterList .section:hover {
  background: #f5f5f5 !important;
}

.j-hoverhide {
  display: none;
}

.m-chapterList .section:hover .j-hoverhide {
  display: block;
}
</style>
