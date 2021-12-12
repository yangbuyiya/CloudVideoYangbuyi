/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_user.controller;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yangbuyi.common_utils.utils.JwtUtils;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.api_user.entity.User;
import top.yangbuyi.service_user.service.UserService;
import top.yangbuyi.service_user.utils.HttpClientUtil;
import top.yangbuyi.service_user.utils.WxConstantUtils;

import java.util.Map;

/**
 * 门户系统用户微信登陆
 *
 * @author Yang Shuai
 * @date 2021/10/13
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/service_user/user/wx")
@RequiredArgsConstructor
@Slf4j
public class WechatController {
    private final UserService userService;
    // 记录当前是否已经登录
    private boolean isLogin;
    // 记录当前是否扫码登录失败
    private volatile boolean isLoginFail;
    // 记录当前登录用户token
    private String jwtToken;

    /**
     * 检查微信登录是否成功
     */
    @GetMapping("/checkLogin")
    public ResponseResult checkLogin() {
        ResponseResult result = null;
        int flag = 1;
        while (true) {
            log.info("开始检查是否登录成功:" + this.isLogin);
            if (this.isLogin) {
                result = ResponseResult.ok().data("token", this.jwtToken);
                // 状态复位
                this.isLogin = false;
                break;
            }
            // 扫描登录失败 返回错误信息
            if (isLoginFail) {
                result = ResponseResult.error("登录失败!");
            }
            try {
                //每500毫秒查询一下状态
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (flag > 50) { //超时处理 25秒
                result = ResponseResult.error("用户登录超时请重新扫码登录!");
                break;
            }
            // 五百毫秒超时
            flag++;
        }
        return result;
    }

    /**
     * 用户扫码成功->微信会调用该回调地址
     * 微信登录回调地址
     *
     * @param code
     * @param state
     * @return 返回登录成功token
     */
    @RequestMapping("/callBack")
    public void callBack(String code, String state) {
        if ("fm".equalsIgnoreCase(state)) {
            try {
                // 1.换取access_token
                String getToken = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                        "appid=%s" +
                        "&secret=%s" +
                        "&code=%s" +
                        "&grant_type=authorization_code";
                //拼接请求地址
                getToken = String.format(getToken,
                        WxConstantUtils.WX_OPEN_APP_ID,
                        WxConstantUtils.WX_OPEN_APP_SECRET,
                        code);
                // 2.发送请求换取token
                //回调获得code，通过用户授权的code去获取微信令牌
                String token = HttpClientUtil.get(getToken);
                Map<String, Object> map = JSON.parseObject(token);
                log.info("请求获取微信token：" + JSON.parseObject(token));
                //获取到了关键的令牌和openid后，
                //就可以正式开始查询微信用户的信息，完成我们要做的微信绑定
                String accessToken = (String) map.get("access_token");
                String openid = (String) map.get("openid");
                // 3.根据openId判断是否有改用户
                User user = this.userService.getByOpenid(openid);
                if (user == null) {
                    // 获取微信用户详细信息
                    String userInfo = "" + "https://api.weixin.qq.com/sns/userinfo?" +
                            "access_token=%s" +
                            "&openid=%s";
                    userInfo = String.format(userInfo, accessToken, openid);
                    // 获取微信用户信息
                    String wxUserInfo = HttpClientUtil.get(userInfo);
                    // 解析用户json信息
                    Map<String, Object> info = JSON.parseObject(wxUserInfo);
                    log.info("微信用户详细信息：" + info.toString());
                    // 获取昵称与头像
                    String nickname = (String) info.get("nickname");
                    String headimgurl = (String) info.get("headimgurl");
                    // 向数据库中插入一条记录
                    user = new User();
                    user.setNickname(nickname);
                    user.setOpenid(openid);
                    user.setAvatar(headimgurl);
                    // 保存用户信息
                    userService.save(user);
                    log.info("插入用户成功:" + user);
                }
                // 生成token
                jwtToken = JwtUtils.getJwtToken(user.getId(), user.getNickname());
                // 更新登录成功标识
                this.isLogin = true;
            } catch (Exception e) {
                e.printStackTrace();
                // 登录失败
                this.isLoginFail = true;
            }
        }
    }
}
