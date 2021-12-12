<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<!-- 作者列表页 -->
<template>
  <div id="videoList" class="bg-fa of">
    <section class="container" style="min-height: 600px;">
      <section class="c-sort-box unBr">
        <div>
          <!--列表展示-->
          <article class="i-author-list" style="padding-top: 50px;">
            <div class="yd-open-course-card open-course-card" style="overflow:hidden;" v-for="author in list" :key="author.id">
              <a :href="'/author/'+author.id" style="color: #7F828B;text-decoration:none;">
                <div class="yd-open-course-card_avatar">
                  <img :src="author.avatar" :alt="author.name"></div>
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
          <!--没有数据-->
          <section class="no-data-wrap" v-if="total==0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据...</span>
          </section>
        </div>
        <!--分页-->
        <el-pagination
          style="padding: 30px 0; text-align: center"
          background
          :current-page="page"
          :page-size="limit"
          layout="total, prev, pager, next, jumper "
          :total="total"
          @current-change="getData"
        />

      </section>

    </section>
  </div>
</template>

<script>
import author from "@/api/author"

export default {
  asyncData({params, error}) {
    return author.authorWebList(1, 3).then(response => {
      if (response.success === true) {
        return {
          list: response.data.rows,  /*作者信息*/
          total: response.data.total  /*作品列表*/
        }
      }
    })
  },
  data() {  //定义变量与初始值
    return {
      list: null, // 数据列表
      total: 0, // 总记录数
      page: 1, // 页码
      limit: 3, // 每页记录数
    }
  },
  methods: {
    //分页切换  page:当前页
    getData(page = 1) { // 调用api层获取数据库中的数据
      this.page = page
      author.authorWebList(this.page, this.limit).then(response => {
        if (response.success === true) {
          this.list = response.data.rows
          this.total = response.data.total
        }
      })
    },
  }
}
</script>

<style scoped>

::v-deep .el-pagination.is-background .el-pager li:not(.disabled).active {
  background-color: orangered;
  color: #fff;
}

.open-course-card {
  width: 375px;
  margin-top: 5px;
  background: white;
}

.yd-open-course-card_avatar {
  float: left;
  width: 80px;
  height: 102px;
  margin-right: 20px;
}

.yd-open-course-card_avatar img {
  width: 100%;
  height: 100%;
  border-radius: 4px;
}

.yd-open-course-card {
  font-family: PingFang SC;
  padding: 12px;
  box-sizing: border-box;
  border: 1px solid rgba(153, 153, 153, 0.15);
  border-radius: 8px;
  height: 143px;
  min-width: 300px;
  max-width: 385px;
  cursor: pointer;
  float: left;
  margin-right: 5px;
}

.yd-open-course-card_content_teacher_tag {
  display: inline-block;
  height: 17px;
  font-weight: 600;
  font-size: 12px;
  line-height: 17px;
  color: #f4ad0b;
  background: rgba(244, 173, 11, 0.06);
  border: 0.5px solid rgba(244, 173, 11, 0.4);
  border-radius: 4px;
  padding: 2px 4px;
  margin-bottom: 10px;
}
</style>
