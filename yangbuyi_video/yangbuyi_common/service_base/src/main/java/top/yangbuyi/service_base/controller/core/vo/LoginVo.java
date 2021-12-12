/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_base.controller.core.vo;

import lombok.Data;

/**
 * @program: yangbuyi_video
 * @ClassName: LoginVo
 * @create: 2021-10-13 01:07
 * @author: Yang Shuai
 * @since： JDK1.8
 * @LoginVo: 门户页面登陆$
 **/

@Data
public class LoginVo {
    /**手机号*/
    private String phone;
    /**密码*/
    private String password;
}

