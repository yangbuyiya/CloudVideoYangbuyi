/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import top.yangbuyi.common_utils.constant.Constants;
import top.yangbuyi.service_security.filter.TokenAuthenticationFilter;
import top.yangbuyi.service_security.filter.TokenLoginFilter;
import top.yangbuyi.service_security.security.DefaultPasswordEncoder;
import top.yangbuyi.service_security.security.TokenLogoutHandler;
import top.yangbuyi.service_security.security.TokenManager;
import top.yangbuyi.service_security.security.UnauthorizedEntryPoint;

/**
 * @program: yangbuyi_video
 * @ClassName: TokenWebSecurityConfig
 * @create: 2021-10-02 21:53
 * @author: Yang Shuai
 * @since： JDK1.8
 * @TokenWebSecurityConfig: springSecurity配置
 **/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 自定义查询数据库类
     */
    private final UserDetailsService userDetailsService;

    /**
     * 令牌管理器
     */
    private final TokenManager tokenManager;
    /**
     * 默认密码编码器
     */
    private final DefaultPasswordEncoder defaultPasswordEncoder;

    /**
     * redis 模板
     */
    private final RedisTemplate<String,Object> redisTemplate;

    /**
     * 自定义查询数据库
     */
    @Autowired
    public TokenWebSecurityConfig (UserDetailsService userDetailsService, DefaultPasswordEncoder defaultPasswordEncoder,
                                   TokenManager tokenManager, RedisTemplate<String, Object> redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 配置设置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().logout().logoutUrl(Constants.LOGIN_OUT_URL)
                .addLogoutHandler(new TokenLogoutHandler(tokenManager, redisTemplate)).and()
                .addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate)).httpBasic();
    }

    /**
     * 配置用户实现类和密码生成
     *
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }

    /**
     * 配置不拦截
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
//        AntPathRequestMatcher[] array =
//                {new AntPathRequestMatcher("/file/**"),
//                        new AntPathRequestMatcher("/profile/**"),
//                        new AntPathRequestMatcher("/wspoint/**")};
//        web.ignoring().requestMatchers(array);

        web.ignoring().antMatchers("/api/**", "/wspoint/**");

    }
}