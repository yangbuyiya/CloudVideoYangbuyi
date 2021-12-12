/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.common_utils.constant;

import java.nio.charset.StandardCharsets;

/**
 * @program: yangbuyi_video
 * @ClassName: Constants
 * @create: 2021-09-22 00:16
 * @author: Yang Shuai
 * @since： JDK1.8
 * @Constants: 通用常量$
 **/

public class Constants {
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 登录用户 redis key
     */
    public static final String LANGS_KEY = "login_langs:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 用户ID
     */
    public static final String JWT_USERID = "userid";

    /**
     * 用户名称
     */
    public static final String JWT_USERNAME = "sub";

    /**
     * 用户头像
     */
    public static final String JWT_AVATAR = "avatar";

    /**
     * 创建时间
     */
    public static final String JWT_CREATED = "created";

    /**
     * 用户权限
     */
    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";


    /**
     * 作品状态->发布
     */
    public final static String CONTENT_STATUS_NORMAL = "Normal";


    /**
     * 作品状态->未发布
     */
    public final static String CONTENT_STATUS_DRAFT = "Draft";

    /**
     * 菜单一级id
     */
    public static final String MENU_PID = "0";

    /**
     * 管理员标识
     */
    public static final String ADMIN = "admin";

    /**
     * TOKEN失效 时间  1天
     */
    public static final long TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000;

    /**
     * 时间10080分钟 七天
     */
    public static final long EXPIRATION_TIME_MINUTES = 7 * 24 * 60;

    /**
     * 时间604,800秒  七天
     */
    public static final long EXPIRATION_TIME_SECONDS = 7 * 24 * 60 * 60;

    /**
     * 令牌密钥 key
     */
    public static final byte[] TOKEN_SECRET_KEY = "yangbuyi".getBytes(StandardCharsets.UTF_8);

    /**
     * 登出
     */
    public static final String LOGIN_OUT_URL = "/service_authority/admin/index/logout";

    /**
     * 登陆
     * /service_auth/admin/index/login
     */
    public static final String LOGIN_URL = "/service_authority/admin/index/login";
}
