<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->
<template>
  <div class="in-wrap">
    <!--头部布局-->
    <header id="header">
      <section class="container">
        <h1 id="logo">
          <a href="#" title="易课堂">
            <img src="~/assets/img/logo.png" width="80" height="76" alt="易课堂">
          </a>
        </h1>
        <div class="h-ResponseResult-nsl">
          <ul class="nav">
            <router-link to="/" tag="li" active-class="current" exact>
              <a>首页</a>
            </router-link>

            <router-link to="/video" tag="li" active-class="current">
              <a>全部视频</a>
            </router-link>
            <router-link to="/author" tag="li" active-class="current">
              <a>创作者</a>
            </router-link>
            <router-link to="/article" tag="li" active-class="current">
              <a>资讯信息</a>
            </router-link>
            <router-link to="/qa" tag="li" active-class="current">
              <a>学习交流</a>
            </router-link>
          </ul>
          <!-- / nav -->
          <ul class="h-ResponseResult-login">
            <li id="no-login" v-if="!loginInfo.id">
              <a href="/login">
                <em class="icon18 login-icon">&nbsp;</em>
                <span class="vam ml5">登录/注册</span>
              </a>
            </li>
            <!--退出-->
            <li v-if="loginInfo.id" id="is-login-two" class="h-ResponseResult-user">
              <a href="#" title>
                <img
                  :src="loginInfo.avatar"
                  width="30"
                  height="30"
                  class="vam picImg"
                  alt
                >
                <span id="userName" class="vam disIb">{{ loginInfo.nickname }}</span>
              </a>
              <a href="javascript:void(0);" title="退出" @click="logout()" class="ml5">退出</a>
            </li>
          </ul>
          <aside class="h-ResponseResult-search">
            <form action="#" method="post">
              <label class="h-ResponseResult-s-box">
                <input type="text" placeholder="搜索..." value>
                <button type="submit" class="s-btn">
                  <em class="icon18">&nbsp;</em>
                </button>
              </label>
            </form>
          </aside>
        </div>
        <aside class="mw-nav-btn">
          <div class="mw-nav-icon"></div>
        </aside>
        <div class="clear"></div>
      </section>
    </header>
    <!--引入主体-->
    <nuxt/>
    <!--尾部布局-->
    <div class="footer" style="height: 121px; background-color: #242A39; width: 100%;text-align: center;">
      <div class="">
        <div class="three links">友情链接：<a style="color: #ffff;" href="https://tool.oschina.net/codeformat/js/"
                                         target="_blank">在线代码格式化</a><a style="color: #ffff;"> &nbsp;|&nbsp; </a><a
          style="color: #ffff;" href="https://www.processon.com/" target="_blank">在线UML</a><a style="color: #ffff;"></a>
        </div>
        <div class="footer-words">
          <span>版权所有 © 2020南京疯码教育科技有限公司 &nbsp; </span><span><a href="http://www.beian.miit.gov.cn" target="_blank"
                                                               style="color: #C0C4CC;">苏ICP备20019650号-1</a> &nbsp;</span>
          疯码教育技术有限公司 <a style="padding-left: 14px;color:rgba(246,246,246,1);" href="/sitemap.html">网站地图</a></div>
      </div>
    </div>
  </div>
</template>
<script>
import "~/assets/css/reset.css";
import "~/assets/css/theme.css";
import "~/assets/css/global.css";
import "~/assets/css/web.css";
import "~/assets/css/content.css";
import cookie from "js-cookie";
import loginApi from "~/api/login"

export default {
  data() {
    return {
      loginInfo: {
        id: '',
        avatar: '',
        nickname: ''
      }
    }
  },
  mounted() {
    /*获取token*/
    this.token = this.$route.query.token
    if (this.token) {//判断路径是否有token值
      /*存储token*/
      cookie.set('login_token', this.token, {domain: 'localhost'})
      /*获取用户信息*/
      loginApi.getLoginInfo()
        .then(response => {
          this.loginInfo = response.data.userInfo
          cookie.set('portal_user', this.loginInfo, {domain: 'localhost'})
        })
    }
    // 重新获取用户信息
    this.showInfo();
  },
  methods: {
    // 解析存储的用户信息
    showInfo() {
      let jsonStr = cookie.get("portal_user");
      if (jsonStr) {
        this.loginInfo = JSON.parse(jsonStr)
      }
    },
    // 退出
    logout() {
      cookie.set('portal_user', "", {domain: 'localhost'})
      cookie.set('login_token', "", {domain: 'localhost'})
      //跳转页面
      window.location.href = "/"
    }
  }
};
</script>
<style>
a {
  color: #919191;
  text-decoration: none !important;
}

a:hover {
  color: #787d82;
}
</style>
