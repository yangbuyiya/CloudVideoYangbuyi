/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_base.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.yangbuyi.common_utils.utils.ResponseResult;

/**
 * @program: yangbuyi_viedo
 * @ClassName: GlobalExceptionHandler
 * @create: 2021-09-07 17:41
 * @author: Yang Shuai
 * @since： JDK1.8
 * @GlobalExceptionHandler: $
 **/

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局异常
     *
     * @param e
     * @return ResponseResult
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult error (Exception e) {
        log.error("全局异常处理:{}", e.getCause().getMessage());
        return ResponseResult.error("操作失败，请练习管理员修复!: {" + e.getCause().getMessage() + "}");
    }


    /**
     * 业务错误
     *
     * @param e e
     * @return {@link ResponseResult}
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseResult businessError (BusinessException e) {
        return ResponseResult.error().code(e.getCode()).message(e.getMsg());
    }


}
