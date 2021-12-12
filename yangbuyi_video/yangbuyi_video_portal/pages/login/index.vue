<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div class="main">
    <div class="login-up-container">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="用户登录" name="first"></el-tab-pane>
        <el-tab-pane label="立即注册" name="second"></el-tab-pane>
        <div class="login-up-container">
          <el-form ref="userForm" :model="params">
            <!--用户昵称-->
            <el-form-item class="input-prepend restyle" prop="nickname"
                          v-if="!isLogin">
              <div>
                <el-input type="text" placeholder="用户昵称" v-model="params.nickname"/>
                <i class="iconfont icon-user"/>
              </div>
            </el-form-item>
            <!--用户手机号-->
            <el-form-item class="input-prepend restyle no-radius">
              <div>
                <el-input type="text" placeholder="手机号" v-model="params.phone"/>
                <i class="iconfont icon-phone"/>
              </div>
            </el-form-item>
            <!--用户密码-->
            <el-form-item class="input-prepend restyle no-radius" prop="password"
                          v-if="isLogin">
              <div>
                <el-input type="text" placeholder="用户密码"
                          v-model="params.password"/>
                <i class="iconfont icon-password"/>
              </div>
            </el-form-item>

            <!--验证码-->
            <el-form-item class="input-prepend restyle no-radius" prop="code"
                          v-if="!isLogin">
              <div style="width: 100%;display: block;float: left;position: relative">
                <el-input type="text" placeholder="验证码"
                          v-model="params.code"/>
                <i class="iconfont icon-phone"/>
              </div>
              <div class="btn" style="position:absolute;right: -10px;top: 6px;width: 40%;">
                <a href="javascript:" type="button"
                   @click="getCodeFun()"
                   :value="codeValue" style="color: orangered">
                  {{ codeValue }}
                </a>
              </div>
            </el-form-item>
            <!--设置密码-->
            <el-form-item class="input-prepend" prop="password" v-if="!isLogin">
              <div>
                <el-input type="password" placeholder="设置密码" v-model="params.password"/>
                <i class="iconfont icon-password"/>
              </div>
            </el-form-item>

            <div class="btn">
              <input type="button" v-model="btn_value" class="login-up-button" @click="btnClick()">
            </div>
            <p class="login-up-msg" v-if="!isLogin">
              点击 “注册” 即表示您同意并愿意遵守简书
              <br>
              <a target="_blank" href="http://www.jianshu.com/p/c44d171298ce">用户协议</a>
              和
              <a target="_blank" href="http://www.jianshu.com/p/2ov8x3">隐私政策</a> 。
            </p>
          </el-form>
          <!-- 更多注册方式 -->
          <div class="more-sign">
            <h6>社交帐号直接注册</h6>
            <ul>
              <li>
                <!--                <a id="weixin" class="weixin" target="_blank" @click="wxDialog=true"-->
                <!--                   href="http://huaan.free.idcfengye.com/api/ucenter/wx/login">-->
                <!--                  <i class="iconfont icon-weixin"/></a>-->
                <a id="weixin" class="weixin" href="#" @click="wxDialog=true">
                  <i class="iconfont icon-weixin"/></a>
              </li>
              <li>
                <a id="qq" class="qq" target="_blank" href="#"><i class="iconfont icon-qq"/></a>
              </li>
            </ul>
          </div>
        </div>
      </el-tabs>
    </div>
    <!--@opened 对话框打开后的回调-->
    <el-dialog
      :visible.sync="wxDialog"
      @opened="wxOpen"
      width="30%"
      center>
      <div id="qrCode" style="padding-left: 110px;"></div>
    </el-dialog>
  </div>
</template>
<script>
import 'assets/css/login.css'
import '~/assets/css/iconfont.css'
import registerApi from "@/api/register";
import loginApi from "@/api/login";
import cookie from "js-cookie";

export default {
  // 用于其他页面引入进来
  layout: 'loginLayout',
  // 微信登录插件
  head: {
    script: [
      {src: 'http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js'}
    ]
  },
  data() {
    return {
      isLogin: true, //记录当前是否为登录状态
      activeName: 'first', //记录当前选择的tabs
      params: { //封装注册输入数据
        phone: '',
        code: '',  //验证码
        nickname: '',//用户昵称
        password: ''
      },
      isSend: true,      //是否发送验证码
      second: 60,        //倒计时间
      codeValue: '获取验证码',
      btn_value: '登录',
      wxDialog: false  // 微信弹出
    }
  },
  methods: {
    /* 自动调用 */
    wxOpen() {
      this.wxDialog = true
      //生成登录二维码
      this.wxCreate();
      //验证是否已经扫码
      console.log("检查扫码是否成功");
      loginApi.checkLogin().then(response => {
        if (response.success) {
          // 携带token跳转到首页
          cookie.set('login_token', response.data.token, {domain: 'localhost'})
          this.$router.push({path: '/', query: {token: response.data.token}})
        } else {
          this.wxDialog = false
          this.$message({
            type: 'error',
            message: response.message
          });
          this.wxOpen();
        }
      }).catch(err => {
        // 处理扫码超时重新渲染
        this.wxDialog = false
        this.wxOpen();
      })
    },
    /* 创建微信登录二维码 */
    wxCreate() {
      let wx = new WxLogin({
        self_redirect: true,
        id: "qrCode",
        appid: "wx92b6693b8c01fc87",
        scope: "snsapi_login",
        redirect_uri: "http://www.sphskla.cn/service_user/user/wx/callBack",
        state: "fm",
        style: "black",
        href: ""
      });
    },
    btnClick() {
      if (this.isLogin) {/*登录*/
        loginApi.login(this.params).then(res => {
          if (res.success) {
            //把token存在cookie中
            cookie.set('login_token', res.data.token, {domain: 'localhost'})
            //登录成功根据token获取用户信息
            loginApi.getLoginInfo().then(response => {
              //将用户信息记录cookie
              cookie.set('portal_user', response.data.userInfo, {domain: 'localhost'})
              // 跳转页面
              window.location.href = "/";
            })
          }
        });
      } else {/*注册*/
        registerApi.register(this.params)
          .then(response => {
            //提示注册成功
            this.$message({
              type: 'success',
              message: "注册成功"
            })
            //跳转登录页面
            this.activeName = "first";
            this.isLogin = true;
            this.btn_value = "登录";
          })
      }
    },
    // 切换按钮名称
    handleClick(tab, event) {
      if (tab.index == 0) {
        this.isLogin = true;
        this.btn_value = "登录";
      } else {
        this.isLogin = false;
        this.btn_value = "注册";
      }
    },
    checkPhone(rule, value, callback) {
      if (!(/^1[34578]\d{9}$/.test(value))) {
        return callback(new Error('手机号码格式不正确'))
      }
      return callback()
    },
    // 获取验证码
    timeDown() {
      let result = setInterval(() => {
        // 定时器 - 1
        --this.second;
        this.codeValue = this.second + "秒后重发";
        if (this.second < 1) {
          // 清除定时器
          clearInterval(result);
          this.isSend = true;
          this.second = 60;
          this.codeValue = "获取验证码"
        }
      }, 1000);
    },
    // 通过输入手机号发送验证码
    getCodeFun() {
      if (this.isSend) {
        registerApi.sendCode(this.params.phone)
          .then(response => {
            // 调用倒计时的方法
            this.timeDown()
            this.isSend = false;
            //提示注册成功
            this.$message({
              type: 'success',
              message: "短信发送成功"
            });
          })
      }
    }
  }
}
</script>
<style></style>
