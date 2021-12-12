<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<!--作者详情页-->
<template>

  <section class="container">
    <header class="comm-title">
      <h2 class="fl tac">
        <span class="c-333">作者介绍</span>
      </h2>
    </header>
    <div class="t-infor-wrap">
      <!-- 作者基本信息 -->
      <section class="fl t-infor-box c-desc-content">
        <div class="mt20 ml20">
          <section class="t-infor-pic">
            <img :src="author.avatar" alt="图片">
          </section>
          <h3 class="hLh30">
            <span class="fsize24 c-333">
              {{ author.name }}&nbsp; {{ author.level === 1 ? '普通作者' : '特邀作者' }}
            </span>
          </h3>
          <section class="mt10">
            <span class="t-tag-bg">{{ author.intro }}</span>
          </section>
          <section class="t-infor-txt">
            <p class="mt20">{{ author.career }}</p>
          </section>
          <div class="clear"></div>
        </div>
      </section>
      <div class="clear"></div>
    </div>
    <section class="mt30">
      <div>
        <header class="comm-title all-teacher-title c-course-content">
          <h2 class="fl tac">
            <span class="c-333">作品信息</span>
          </h2>
          <section class="c-tab-title">
            <a href="javascript: void(0)">&nbsp;</a>
          </section>
        </header>
        <!--没有数据-->
        <section class="no-data-wrap" v-if="contentList.length <= 0">
          <em class="icon30 no-data-ico">&nbsp;</em>
          <span class="c-666 fsize14 ml10 vam">该作品还未发布作品</span>
        </section>
        <article class="comm-video-list">
          <ul class="of" id="bna">
            <li v-for="content in contentList" :key="content.id">
              <div class="cc-l-wrap">
                <section class="video-img">
                  <img :src="content.cover" class="img-responsive">
                  <div class="cc-mask">
                    <a :href="'/video/'+content.id" class="comm-btn c-btn-1">播放</a>
                  </div>
                </section>
                <h3 class="hLh30 txtOf mt10">
                  <a :href="'/video/'+content.id" class="course-title fsize18 c-333">{{ content.title }}</a>
                </h3>
                <section class="mt10 hLh20 of">
                      <span class="fr jgTag bg-orange" v-if="Number(content.price) === 0">
                        <i class="c-fff fsize12 f-fA">免费</i>
                        </span>
                  <span class="fr jgTag bg-orange" v-else>
                        <i class="c-fff fsize12 f-fA"> ￥{{ content.price }}</i>
                        </span>
                  <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA">{{ content.buyCount }} 人学习</i>
                                                |
                        <i class="c-999 f-fA">{{ content.viewCount }} 人浏览</i>
                        </span>
                </section>
              </div>
            </li>
          </ul>
          <div class="clear"></div>
        </article>
      </div>
    </section>
  </section>

</template>

<script>
import author from "@/api/author"

export default {
  /*接收参数
  * params内容为路由参数 */
  asyncData({params, error}) {
    return author.getAuthorById(params.id).then(response => {
      return {
        author: response.data.author,  /*作者信息*/
        contentList: response.data.contentList  /*作品列表*/
      }
    })
  }
}
</script>

<style scoped>

</style>
