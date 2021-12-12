/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_authority.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.service_authority.service.IndexService;
import top.yangbuyi.service_security.utils.SpringSecurityBean;

import java.util.List;
import java.util.Map;

/**
 * @program: yangbuyi_video
 * @ClassName: IndexController
 * @create: 2021-10-03 21:35
 * @author: Yang Shuai
 * @since： JDK1.8
 * @IndexController: 用户登入入口$
 **/

@RestController
@Slf4j
@RequestMapping("/service_authority/admin/index")
@RequiredArgsConstructor
public class IndexController {

    private final IndexService indexService;

    /**
     * 根据token获取用户信息
     * @return ResponseResult
     */
    @GetMapping("info")
    public ResponseResult info(){
        //获取当前登录用户用户名
        String username = SpringSecurityBean.userName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return ResponseResult.ok().data(userInfo);
    }


    /**
     * 根据用户获取拥有的菜单
     *
     * @return {@link ResponseResult}
     */
    @GetMapping("menu")
    public ResponseResult getMenu(){
        // 获取当前登录用户用户名
        String username = SpringSecurityBean.userName();
        List<JSONObject> menuList = indexService.getMenuByUserName(username);
        return ResponseResult.ok().data("menuList", menuList);
    }

}
