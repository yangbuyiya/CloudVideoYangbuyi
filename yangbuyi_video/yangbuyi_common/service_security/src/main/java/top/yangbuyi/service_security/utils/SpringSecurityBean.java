/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_security.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import top.yangbuyi.service_security.entity.User;

/**
 * @program: yangbuyi_video
 * @ClassName: getUserInfo
 * @create: 2021-10-03 21:59
 * @author: Yang Shuai
 * @since： JDK1.8
 * @getUserInfo: 获取当前登陆的用户信息$
 **/
@Data
public class SpringSecurityBean {


    /**
     * 获取用户
     *
     * @return {@link Authentication}
     */
    public static Authentication user () {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获得用户名
     *
     * @return {@link String}
     */
    public static String userName () {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
