/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_base.controller.core.vo;

import lombok.Data;

/**
 * @program: yangbuyi_video
 * @ClassName: RegisterVo
 * @create: 2021-10-13 01:08
 * @author: Yang Shuai
 * @since： JDK1.8
 * @RegisterVo: 门户注册$
 **/

@Data
public class RegisterVo {
    /**昵称*/
    private String nickname;
    /**手机号*/
    private String phone;
    /**密码*/
    private String password;
    /**验证码*/
    private String code;
}