/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.yangbuyi.common_utils.utils.ResponseResult;

/**
 * @program: yangbuyi_video
 * @ClassName: LoginController
 * @create: 2021-09-07 19:14
 * @author: Yang Shuai
 * @since： JDK1.8
 * @LoginController: 登录$
 **/


@RestController
@RequestMapping("/service_video/user")
public class LoginController {

    @PostMapping("/login")
    public ResponseResult login () {
        return ResponseResult.ok().data("token", "admin-token");
    }

    @GetMapping("/info")
    public ResponseResult info () {
        return ResponseResult.ok()
                       .data("roles", "[admin]")
                       .data("name", "fmjava")
                       .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
