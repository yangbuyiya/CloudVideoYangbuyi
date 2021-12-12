/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_authority.security;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import top.yangbuyi.service_authority.entity.User;
import top.yangbuyi.service_authority.service.MenuService;
import top.yangbuyi.service_authority.service.UserService;
import top.yangbuyi.service_security.entity.SecurityUser;

import java.util.List;

/**
 * @program: yangbuyi_video
 * @ClassName: UserDetailsServiceImpl
 * @create: 2021-10-03 21:10
 * @author: Yang Shuai
 * @since： JDK1.8
 * @UserDetailsServiceImpl: 重写用户实现类$重写service_security模块UserDetailsService
 **/
@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    /**
     * 用户服务
     */
    private final UserService userService;

    /**
     * 权限信息
     */
    private final MenuService menuService;

    /***
     * 根据账号获取用户信息
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        User user = userService.selectByUsername(username);

        // 判断用户是否存在
        if (null == user) {
//            throw new BusinessException("用户名或密码不正确!");
            throw new UsernameNotFoundException("用户名或密码不正确!");
        } else {
            // 返回UserDetails实现类
            top.yangbuyi.service_security.entity.User curUser = new top.yangbuyi.service_security.entity.User();
            BeanUtils.copyProperties(user, curUser);

            List<String> authorities = menuService.selectPermissionValueByUserId(user.getId());
            SecurityUser securityUser = new SecurityUser(curUser);
            securityUser.setPermissionValueList(authorities);
            return securityUser;
        }
    }

}

