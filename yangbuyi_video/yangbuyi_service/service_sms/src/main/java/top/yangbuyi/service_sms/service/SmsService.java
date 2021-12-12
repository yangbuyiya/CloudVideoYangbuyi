/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_sms.service;

import org.springframework.cloud.openfeign.FeignClient;

import java.util.Map;

/**
 * @program: yangbuyi_video
 * @ClassName: SmsService
 * @create: 2021-10-12 23:44
 * @author: Yang Shuai
 * @since： JDK1.8
 * @SmsService: $
 **/

public interface SmsService {
    /**
     * 短信发送验证码
     * @param phone
     * @param sms_169111508
     * @param param
     * @return boolean
     */
    boolean send (String phone, Map<String, Object> param);
}
