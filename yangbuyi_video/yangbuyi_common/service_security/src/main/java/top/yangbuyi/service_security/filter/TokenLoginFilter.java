/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_security.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import top.yangbuyi.common_utils.constant.Constants;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.common_utils.utils.ResponseUtil;
import top.yangbuyi.service_security.entity.SecurityUser;
import top.yangbuyi.service_security.entity.User;
import top.yangbuyi.service_security.security.TokenManager;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 系统登陆认证过滤器 ->
 * 登录令牌过滤器
 * 登录过滤器，继承UsernamePasswordAuthenticationFilter，对用户名密码进行登录校验
 *
 * @author Yang Shuai
 * @date 2021/10/03
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 身份验证管理器
     */
    private final AuthenticationManager authenticationManager;

    /**
     * 令牌管理器
     */
    private final TokenManager tokenManager;

    /**
     * 复述,模板
     */
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 登录令牌过滤器
     *
     * @param authenticationManager 身份验证管理器
     * @param tokenManager          令牌管理器
     * @param redisTemplate         复述,模板
     */
    public TokenLoginFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate<String, Object> redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(Constants.LOGIN_URL, "POST"));
    }


    /**
     * 重写 -> 尝试身份验证逻辑
     * 跳转到user
     *
     * @param req 要求的事情
     * @param res res
     * @return {@link Authentication}
     * @throws AuthenticationException 身份验证异常
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 重写 -> 登录成功逻辑
     *
     * @param req
     * @param res
     * @param chain
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        SecurityUser user = (SecurityUser) auth.getPrincipal();
        String token = tokenManager.createToken(user.getCurrentUserInfo().getUsername());
        // 将登陆成功的用户信息保存到缓存
        redisTemplate.opsForValue().set(user.getCurrentUserInfo().getUsername(), user.getPermissionValueList());
        // 用于判断是否多人登陆
        redisTemplate.opsForValue().set(Constants.LOGIN_TOKEN_KEY + user.getCurrentUserInfo().getUsername(), token, Constants.EXPIRATION_TIME_SECONDS, TimeUnit.SECONDS);
        ResponseUtil.out(res, ResponseResult.ok().data("token", token));
    }

    /**
     * 重写 -> 登录失败逻辑
     *
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.out(response, ResponseResult.error("用户名或密码错误"));
    }
}
