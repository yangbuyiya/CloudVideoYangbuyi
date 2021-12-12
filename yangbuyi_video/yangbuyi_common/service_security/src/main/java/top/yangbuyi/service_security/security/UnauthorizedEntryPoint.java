/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_security.security;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.common_utils.utils.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 未经授权的入口点
 * 未授权的统一处理方式
 * @author Yang Shuai
 * @date 2021/10/03
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {


    /**
     * 授权失败调用
     *
     * @param request       请求
     * @param response      响应
     * @param authException 身份验证异常
     * @throws IOException      ioexception
     * @throws ServletException servlet异常
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();
        ResponseUtil.out(response, ResponseResult.error(authException.getLocalizedMessage()));
    }
}

