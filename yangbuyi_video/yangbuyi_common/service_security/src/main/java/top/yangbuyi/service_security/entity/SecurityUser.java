/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_security.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @program: yangbuyi_video
 * @ClassName: SecurityUser
 * @create: 2021-10-02 21:57
 * @author: Yang Shuai
 * @since： JDK1.8
 * @SecurityUser: 安全认证用户详情信息$
 **/

@Data
@Slf4j
public class SecurityUser implements UserDetails {


    /**
     * 当前登录用户
     */
    private transient User currentUserInfo;

    /**
     * 权限标识列表
     */
    private List<String> permissionValueList;

    public SecurityUser () {
    }

    public SecurityUser (User user) {
        if (user != null) {
            this.currentUserInfo = user;
        }
    }


    /**
     * 获取权限信息
     * 重写 将权限植入到安全管理器当中
     * @return {@link Collection<? extends GrantedAuthority>}
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String permissionValue : permissionValueList) {
            if (StringUtils.isEmpty(permissionValue)) {
                continue;
            }
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword () {
        return currentUserInfo.getPassword();
    }

    @Override
    public String getUsername () {
        return currentUserInfo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired () {
        return true;
    }

    @Override
    public boolean isAccountNonLocked () {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired () {
        return true;
    }

    @Override
    public boolean isEnabled () {
        return true;
    }
}

