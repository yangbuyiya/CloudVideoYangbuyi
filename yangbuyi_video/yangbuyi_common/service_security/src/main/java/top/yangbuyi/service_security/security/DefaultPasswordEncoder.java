/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_security.security;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import top.yangbuyi.common_utils.utils.MD5;


/**
 * 密码的处理方法类型
 *
 * @author Yang Shuai
 * @date 2021/10/03
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    /**
     * 默认密码编码器
     */
    public DefaultPasswordEncoder() {
        this(-1);
    }

    /**
     * 默认密码编码器
     *
     * @param strength the log rounds to use, between 4 and 31
     */
    public DefaultPasswordEncoder(int strength) {

    }

    /**
     * 编码
     *
     * @param rawPassword 原始密码
     * @return {@link String}
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5.encrypt(rawPassword.toString());
    }

    /**
     * 匹配
     *
     * @param rawPassword     原始密码
     * @param encodedPassword 编码的密码
     * @return boolean
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
    }
}