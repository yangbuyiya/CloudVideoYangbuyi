/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_authority.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import top.yangbuyi.api_websocket.RemoteWebsocketClient;
import top.yangbuyi.common_utils.utils.MD5;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.service_authority.entity.User;
import top.yangbuyi.service_authority.service.RoleService;
import top.yangbuyi.service_authority.service.UserService;
import top.yangbuyi.service_security.utils.SpringSecurityBean;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author yangbuyi
 * @since 2021-09-27
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/service_authority/admin/user")
@RequiredArgsConstructor
@RefreshScope
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final RemoteWebsocketClient remoteWebsocketClient;

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;


    @RequestMapping("/getConfi/{id}")
    public boolean get(@PathVariable("id") String id) {
        System.out.println(id);
        return useLocalCache;
    }

    @ApiOperation(value = "获取管理用户分页列表")
    @GetMapping("/getUserList/{page}/{limit}")
    public ResponseResult getUserList(
            @PathVariable Long page,
            @PathVariable Long limit,
            User userQueryVo) {
        Page<User> pageParam = new Page<>(page, limit);
        userService.selectPage(pageParam, userQueryVo);
        remoteWebsocketClient.sendMessage(SpringSecurityBean.userName(), "用户：[" + SpringSecurityBean.userName() + "]查询用户分页列表完毕");
        return ResponseResult.ok().dataPage(pageParam.getRecords(), pageParam.getTotal());
    }

    @ApiOperation(value = "根据用户id查询用户")
    @GetMapping("/getUserById/{id}")
    public ResponseResult getUser(@PathVariable String id) {
        User user = userService.getById(id);
        return ResponseResult.ok().data("user", user);
    }

    @ApiOperation(value = "新增管理用户")
    @PostMapping("/saveUser")
    public ResponseResult save(@RequestBody User user) {
        user.setPassword(MD5.encrypt(user.getPassword()));
        userService.save(user);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "修改管理用户")
    @PostMapping("/updateUser")
    public ResponseResult updateById(@RequestBody User user) {
        userService.updateById(user);
        return ResponseResult.ok();
    }


    @ApiOperation(value = "删除管理用户")
    @PostMapping("/removeUser/{id}")
    public ResponseResult remove(@PathVariable String id) {
        userService.removeById(id);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "批量删除用户")
    @PostMapping("/batchRemoveUser")
    public ResponseResult batchRemove(@RequestBody List<String> idList) {
        userService.removeByIds(idList);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/getUserRoleData/{userId}")
    public ResponseResult toAssign(@PathVariable String userId) {
        Map<String, Object> roleMap = roleService.findRoleByUserId(userId);
        return ResponseResult.ok().data(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssignRole")
    public ResponseResult doAssign(@RequestParam String userId, @RequestParam String[] roleId) {
        roleService.saveUserRoleRelationShip(userId, roleId);
        return ResponseResult.ok();
    }


}

