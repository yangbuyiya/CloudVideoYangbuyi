/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.common_utils.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 响应实效
 *
 * @author Yang Shuai
 * @date 2021/10/03
 */
public class ResponseUtil {

    /**
     * 出
     *
     * @param response 响应
     * @param ResponseResult        ResponseResult
     */
    public static void out(HttpServletResponse response, ResponseResult ResponseResult) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(), ResponseResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
