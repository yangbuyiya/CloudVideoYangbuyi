/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_security.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import top.yangbuyi.common_utils.constant.Constants;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.common_utils.utils.ResponseUtil;
import top.yangbuyi.service_security.security.TokenManager;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @program: yangbuyi_video
 * @ClassName: TokenAuthenticationFilter
 * @create: 2021-10-03 14:12
 * @author: Yang Shuai
 * @since： JDK1.8
 * @TokenAuthenticationFilter: 登陆授权拦截校验$
 **/


public class TokenAuthenticationFilter extends BasicAuthenticationFilter {
    private final TokenManager tokenManager;
    private final RedisTemplate<String, Object> redisTemplate;

    public TokenAuthenticationFilter(AuthenticationManager authManager, TokenManager tokenManager, RedisTemplate<String, Object> redisTemplate) {
        super(authManager);
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        // 判断为登陆放行
        if (Constants.LOGIN_URL.contains(req.getRequestURI())) {
            chain.doFilter(req, res);
            return;
        }
        // 根据token信息判断是否有效
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            authentication = getAuthentication(req);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtil.out(res, ResponseResult.error("令牌失效请重新登陆"));
        }

        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            ResponseUtil.out(res, ResponseResult.error("令牌失效请重新登陆"));
        }
        chain.doFilter(req, res);
    }

    /**
     * 授权逻辑
     *
     * @param request 请求
     * @return {@link UsernamePasswordAuthenticationToken}
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // token置于header里
        String token = request.getHeader(Constants.TOKEN);
        if (token != null && !"".equals(token.trim())) {
            String userName = tokenManager.getUserFromToken(token);
            // 构建权限标识 放入security当中
            List<String> permissionValueList = (List<String>) redisTemplate.opsForValue().get(userName);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            assert permissionValueList != null;
            if (permissionValueList.size() > 0) {
                for (String permissionValue : permissionValueList) {
                    if (StringUtils.isEmpty(permissionValue)) {
                        continue;
                    }
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
                    authorities.add(authority);
                }
            }

            if (!StringUtils.isEmpty(userName)) {
                return new UsernamePasswordAuthenticationToken(userName, token, authorities);
            }
            return null;
        }
        return null;
    }
}
