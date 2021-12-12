/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_base.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import top.yangbuyi.common_utils.utils.ResultCode;

/**
 * @program: yangbuyi_video
 * @ClassName: BusinessExcetion
 * @create: 2021-09-20 20:41
 * @author: Yang Shuai
 * @since： JDK1.8
 * @BusinessExcetion: 业务异常$
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private Integer code;
    private String msg;

    public BusinessException(String s) {
        super(s);
        this.code = ResultCode.ERROR;
        this.msg = s;
    }
}
