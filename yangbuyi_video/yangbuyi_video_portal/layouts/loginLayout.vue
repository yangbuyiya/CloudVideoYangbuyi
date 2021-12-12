<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->
<!-- 登陆页面头部 -->
<template>
  <div class="login">
    <!--头部布局-->
    <header id="header">
      <section class="container">
        <h1 id="logo">
          <a href="#" title="疯码课堂">
            <img src="~/assets/img/logo1.png" height="100px" alt="疯码课堂">
          </a>
        </h1>
        <div class="h-ResponseResult-nsl">
          <!--导航-->
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
          <!--搜索-->
          <aside class="h-ResponseResult-search">
            <form action="#" method="post">
              <label class="h-ResponseResult-s-box">
                <input type="text" placeholder="搜索..." name="queryCourse.courseName" value>
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
    <!--页面信息-->
    <nuxt/>
  </div>
</template>
<script></script>
<style></style>
