/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_user.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 微信登陆常量
 *
 * @author Yang Shuai
 * @date 2021/10/13
 */
@Component
public class WxConstantUtils implements InitializingBean {
    /**
     * 应用程序id
     */
    @Value("${wx.open.app_id}")
    private String appId;

    /**
     * 应用程序的秘密
     */
    @Value("${wx.open.app_secret}")
    private String appSecret;

    /**
     * 重定向url
     */
    @Value("${wx.open.redirect_url}")
    private String redirectUrl;

    public static String WX_OPEN_APP_ID;
    public static String WX_OPEN_APP_SECRET;
    public static String WX_OPEN_REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        WX_OPEN_APP_ID = appId;
        WX_OPEN_APP_SECRET = appSecret;
        WX_OPEN_REDIRECT_URL = redirectUrl;
    }
}
