/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_authority.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.yangbuyi.common_utils.utils.ResultCode;
import top.yangbuyi.service_authority.entity.Role;
import top.yangbuyi.service_authority.entity.User;
import top.yangbuyi.service_authority.service.IndexService;
import top.yangbuyi.service_authority.service.MenuService;
import top.yangbuyi.service_authority.service.RoleService;
import top.yangbuyi.service_authority.service.UserService;
import top.yangbuyi.service_base.exception.BusinessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: yangbuyi_video
 * @ClassName: IndexServiceImpl
 * @create: 2021-10-03 21:39
 * @author: Yang Shuai
 * @since： JDK1.8
 * @IndexServiceImpl: $
 **/

@Component
@RequiredArgsConstructor
public class IndexServiceImpl implements IndexService {
    private final UserService userService;
    private final RoleService roleService;
    private final MenuService menuService;
    private final RedisTemplate redisTemplate;

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return {@link Map <String, Object>}
     */
    @Override
    public Map<String, Object> getUserInfo (String username) {
        final HashMap<String, Object> result = new HashMap<>(14);
        User user = userService.selectByUsername(username);

        if (ObjectUtils.isEmpty(user)) {
            throw new BusinessException(ResultCode.ERROR, "用户或密码错误!");
        }

        // 根据用户ID获取拥有的角色
        final List<Role> roles = roleService.selectRoleByUserId(user.getId());
        // 根据用户ID获取拥有的权限
        final List<String> permissionValueList = menuService.selectPermissionValueByUserId(user.getId());
        // 把权限缓存到redis当中
        redisTemplate.opsForValue().set(username, permissionValueList);
        // 组装前端参数
        result.put("name", user.getUsername());
        result.put("avatar", "http://thirdqq.qlogo.cn/g?b=sdk&k=5GQIoAWp5NU2FZN3fofZRw&s=140&t=1555735675");
        result.put("roles", roles.stream().map(Role::getRoleName).collect(Collectors.toList()));
        result.put("permissionValueList", permissionValueList);
        return result;
    }

    /**
     * 根据用户名称查询菜单
     *
     * @param username 用户名
     * @return {@link List< JSONObject >}
     */
    @Override
    public List<JSONObject> getMenuByUserName (String username) {
        User user = userService.selectByUsername(username);
        //根据用户id获取用户菜单权限
        List<JSONObject> permissionList = menuService.selectPermissionByUserId(user.getId());
        return permissionList;
    }
}
