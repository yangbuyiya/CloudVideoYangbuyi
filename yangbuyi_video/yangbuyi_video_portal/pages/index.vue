<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div style="overflow: hidden">
    <!-- 轮播图 开始 -->
    <div class="warper" style="position: relative">
      <div class="warper_content">
        <!--分类-->
        <div class="category">
          <ul>
            <li v-for="(cate,index) in categoryList" v-bind:key="index">
              <div class="coursex-item1">
                <a class="shuang">{{ cate.title }}</a>
              </div>
            </li>
          </ul>
        </div>
        <swiper :options="swiperOptions" class="swiper-container swiper-cus">
          <swiper-slide v-for="banner in bannerList" :key="banner.id" :style="{background:banner.imageBg}">
            <a target="_blank" :href="banner.linkUrl">
              <img style="height: 300px;" :src="banner.imageUrl" :alt="banner.title"/>
            </a>
          </swiper-slide>
          <div class="swiper-pagination swiper-pagination-white pagination" slot="pagination"></div>
          <div style="left: 200px;" class="swiper-button-prev swiper-button-white arrow-left" slot="button-prev"></div>
          <div style="right: 200px" class="swiper-button-next swiper-button-white arrow-right" slot="button-next"></div>
        </swiper>
      </div>
      <div class="">剩余的容器</div>
    </div>
    <!-- 轮播图 结束 -->
    <!--列表-->
    <div id="videoList">
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333" style="float:left; border-left: 3px solid red; padding-left: 10px;">热门视频</span>
              <span class="c-333" style="float:right; font-size: 18px;"><a href="#">更多</a></span>
            </h2>
          </header>
          <div>
            <article class="comm-video-list">
              <ul>
                <li v-for="(content,index) in contentList" class="fl" v-bind:key="index">
                  <div class="cc-l-wrap">
                    <section class="video-img">
                      <img style="width: 268px;height: 148px" :src="content.cover" class="img-responsive" alt="图片">
                      <div class="cc-mask">
                        <!--            跳转视频详情            -->
                        <a :href="'/video/'+content.id" class="comm-btn c-btn-1">播放</a>
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a href="#" class="course-title fsize18 c-333" style="font-weight: bold;">{{ content.title }}</a>
                    </h3>
                    <section class="mt10 hLh20 of">
                      <span class="fr jgTag bg-orangered" v-if="Number(content.price) <= 0">
                        <i class="c-fff fsize12 f-fA">免费</i>
                        </span>
                      <span class="fr jgTag bg-orangered" v-else>
                          <i class="c-fff fsize12 f-fA"> ￥{{ content.price }}</i>
                        </span>
                      <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA">{{ content.buyCount }} 人学习</i>|
                        <i class="c-999 f-fA">{{ content.viewCount }} 人浏览</i>
                        </span>
                    </section>
                  </div>
                </li>
              </ul>

            </article>
          </div>
        </section>
      </div>

      <!--优秀创作者-->
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span class="c-333" style="float:left; border-left: 3px solid red; padding-left: 10px;">优秀创作者</span>
              <span class="c-333" style="float:right; font-size: 18px; "><a href="#">更多</a></span>
            </h2>
          </header>
          <div>
            <article class="i-author-list">

              <div v-for="(author,index) in authorList" v-bind:key="index" class="yd-open-course-card open-course-card"
                   style="overflow:hidden;">
                <a :href='"/author/"+author.id' :title="index.name" style="color: #7F828B;text-decoration:none;">
                  <div class="yd-open-course-card_avatar">
                    <img :alt="author.name" :src="author.avatar">
                  </div>
                  <div class="yd-open-course-card_content_teacher">
                    <div class="yd-open-course-card_content_teacher_tag">{{ author.name }}</div>
                  </div>
                  <div>{{ author.intro }}</div>
                  <span>-------------------------</span>
                  <div>{{ author.career }}</div>
                </a>
              </div>

              <div class="clear"></div>
            </article>
          </div>
        </section>
      </div>
    </div>
  </div>
</template>

<script>

import banner from "@/api/banner";
import index from "@/api/index";
import {Swiper, SwiperSlide} from 'vue-awesome-swiper'

export default {
  components: {Swiper, SwiperSlide},
  data() {
    return {
      swiperOptions: {
        spaceBetween: 30,
        centeredSlides: true,
        autoplay: {
          delay: 2500,
          disableOnInteraction: true,
        },
        pagination: {
          el: ".swiper-pagination",
          clickable: true,
        },
        //配置导航
        navigation: {
          nextEl: ".swiper-button-next", //下一页dom节点
          prevEl: ".swiper-button-prev", //前一页dom节点
        },
      },
    };
  },
  async asyncData({params, error}) {
    let [bannerData, map] = await Promise.all([
      banner.getList(),
      index.getList()
    ])
    return {
      bannerList: bannerData.data.list,
      authorList: map.data.authorList,
      contentList: map.data.contentList,
      categoryList: map.data.categoryList
    }
  }
};
</script>
<style lang="stylus" scoped></style>
