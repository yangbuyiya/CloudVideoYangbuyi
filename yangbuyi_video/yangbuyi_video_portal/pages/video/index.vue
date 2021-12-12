<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div id="aCoursesList" class="bg-fa of">

    <section class="container">
      <header class="comm-title">

      </header>
      <section class="c-sort-box">
        <section class="c-s-dl">
          <dl>
            <dt>
              <span class="c-999 fsize14">作品分类</span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li v-for="(item,index) in oneCategoryList" :key="index" :class="{active:oneIndex==index}">
                  <a :title="item.title" href="#" @click="searchOne(item.id,index)">{{ item.title }} </a>
                </li>
              </ul>
            </dd>
          </dl>
          <dl>
            <dt>
              <span class="c-999 fsize14"></span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li v-for="(item,index) in twoCategoryList" :key="index" :class="{active:twoIndex==index}">
                  <a :title="item.title" href="#" @click="searchTwo(item.id,index)">{{ item.title }}</a>
                </li>
              </ul>
            </dd>
          </dl>
          <div class="clear"></div>
        </section>
        <div class="js-wrap">
          <section class="fr">
            <span class="c-ccc">
              <i class="c-master f-fM">1</i>/
              <i class="c-666 f-fM">1</i>
            </span>
          </section>
          <section class="fl">
            <ol class="js-tap clearfix">
              <li :class="{'current bg-orangered':buyCountSort!=''}">
                <a title="销量" href="javascript:void(0);" @click="searchBuyCount()">畅销
                  <span :class="{hide:buyCountSort==''}">↓</span>
                </a>
              </li>
              <li :class="{'current bg-orangered':gmtCreateSort!=''}">
                <a title="最新" href="javascript:void(0);" @click="searchGmtCreate()">最新
                  <span :class="{hide:gmtCreateSort==''}">↓</span>
                </a>
              </li>
              <li :class="{'current bg-orangered':priceSort!=''}">
                <a title="价格" href="javascript:void(0);" @click="searchPrice()">价格&nbsp;
                  <span :class="{hide:priceSort==''}">↓</span>
                </a>
              </li>
            </ol>
          </section>
        </div>
        <div class="mt40">
          <!-- /无数据提示 开始-->
          <section class="no-data-wrap" v-if="data.length == 0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据...</span>
          </section>

          <article class="comm-video-list" style="min-height: 450px;">
            <ul class="of" id="bna">
              <li v-for="item in data" :key="item.id">
                <div class="cc-l-wrap">
                  <section class="video-img">
                    <img :src="item.cover" style="width: 268px;height: 148px"  class="img-responsive">
                    <div class="cc-mask">
                      <a :href="'/video/'+item.id" class="comm-btn c-btn-1">播放</a>
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a :href="'/video/'+item.id" class="course-title fsize18 c-333">{{ item.title }}</a>
                  </h3>
                  <section class="mt10 hLh20 of">
                    <span class="fr jgTag bg-orangered" v-if="Number(item.price) === 0">
                        <i class="c-fff fsize12 f-fA">免费</i>
                    </span>
                    <span class="fr jgTag bg-orangered" v-else>
                        <i class="c-fff fsize12 f-fA"> ￥{{ item.price }}</i>
                        </span>
                    <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA">{{ item.buyCount }} 人学习</i>
                                                |
                        <i class="c-999 f-fA">{{ item.viewCount }} 人浏览</i>
                    </span>
                  </section>
                </div>
              </li>
            </ul>
            <div class="clear"></div>
          </article>
        </div>

        <!--分页-->
        <el-pagination
          style="padding: 30px 0; text-align: center"
          background
          :current-page="searchObj.page"
          :page-size="searchObj.limit"
          layout="total, prev, pager, next, jumper "
          :total="total"
          @current-change="gotoPage"
        />

      </section>
    </section>
    <!-- /课程列表 结束 -->
  </div>
</template>

<script>
import content from '@/api/content'

export default {
  data() {  //定义变量与初始值
    var th = this;
    return {
      list: null, // 数据列表
      total: 0, // 总记录数
      data: [],
      searchObj: {
        page: 1, // 页码
        limit: 10, // 每页记录数
      }, // 查询表单对象
      oneCategoryList: [], // 一级分类列表
      twoCategoryList: [], // 二级分类列表
      oneIndex: -1,  /*记录一级选中状态角标*/
      twoIndex: -1, /*记录二级选中状态角标*/
      buyCountSort: "", /*畅销排序*/
      gmtCreateSort: "", /*日期排序*/
      priceSort: "" /*价格排序*/
    }
  },
  /**加载数据*/
  async asyncData({params, error}) {
    let [request1Data, request2Data] = await Promise.all([
      content.getPageList({
        page: 1, // 页码
        limit: 10, // 每页记录数
      }),
      content.getAllCategory()
    ])
    return {
      data: request1Data.data.rows,
      total: request1Data.data.total,
      oneCategoryList: request2Data.data.rows,
    }
  },
  methods: {
    //分页查询
    gotoPage(page) {
      this.searchObj.page = page;
      content.getPageList(this.searchObj).then(response => {
        this.data = response.data.rows;
        this.total = response.data.total;
      })
    },

    //点击某个一级分类，查询对应二级分类
    searchOne(categoryParentId, index) {
      //把传递index值赋值给oneIndex,为了active样式生效
      this.oneIndex = index

      this.twoIndex = -1
      this.searchObj.categoryId = ""
      this.twoCategoryList = []

      //把一级分类点击id值，赋值给searchObj
      this.searchObj.categoryParentId = categoryParentId
      //点击某个一级分类进行条件查询
      this.gotoPage(1)

      //拿着点击一级分类id 和 所有一级分类id进行比较，
      //如果id相同，从一级分类里面获取对应的二级分类
      for (let i = 0; i < this.oneCategoryList.length; i++) {
        //获取每个一级分类
        var oneCategory = this.oneCategoryList[i]
        //比较id是否相同
        if (categoryParentId == oneCategory.id) {
          //从一级分类里面获取对应的二级分类
          this.twoCategoryList = oneCategory.children;
        }
      }
    },

    //点击某个二级分类实现查询
    searchTwo(categoryId, index) {
      //把index赋值,为了样式生效
      this.twoIndex = index
      //把二级分类点击id值，赋值给searchObj
      this.searchObj.categoryId = categoryId;
      //点击某个二级分类进行条件查询
      this.gotoPage(1)
    },

    //根据销量排序
    searchBuyCount() {
      //设置对应变量值，为了样式生效
      this.buyCountSort = "1"
      this.gmtCreateSort = ""
      this.priceSort = ""

      //把值赋值到searchObj
      this.searchObj.buyCountSort = this.buyCountSort
      this.searchObj.gmtCreateSort = this.gmtCreateSort;
      this.searchObj.priceSort = this.priceSort;
      this.searchObj.type = 1
      //调用方法查询
      this.gotoPage(1)
    },

    //最新排序
    searchGmtCreate() {
      //设置对应变量值，为了样式生效
      this.buyCountSort = ""
      this.gmtCreateSort = "1"
      this.priceSort = ""

      //把值赋值到searchObj
      this.searchObj.buyCountSort = this.buyCountSort
      this.searchObj.gmtCreateSort = this.gmtCreateSort;
      this.searchObj.priceSort = this.priceSort;
      this.searchObj.type = 2
      //调用方法查询
      this.gotoPage(1)
    },

    //价格排序
    searchPrice() {
      //设置对应变量值，为了样式生效
      this.buyCountSort = ""
      this.gmtCreateSort = ""
      this.priceSort = "1"

      //把值赋值到searchObj
      this.searchObj.buyCountSort = this.buyCountSort
      this.searchObj.gmtCreateSort = this.gmtCreateSort;
      this.searchObj.priceSort = this.priceSort;
      this.searchObj.type = 3

      //调用方法查询
      this.gotoPage(1)
    }
  }

}
</script>

<style scoped>
::v-deep .el-pagination.is-background .el-pager li:not(.disabled).active {
  background-color: orangered;
  color: #fff;
}

.active {
  background: orangered;
}

.active a {
  color: white;
}

.active a:hover {
  color: white;
}

.hide {
  display: none;
}

.show {
  display: block;
}
</style>

