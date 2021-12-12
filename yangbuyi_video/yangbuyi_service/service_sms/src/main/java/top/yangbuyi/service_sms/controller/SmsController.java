/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_sms.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.service_base.exception.BusinessException;
import top.yangbuyi.service_base.utils.text.PhoneUtils;
import top.yangbuyi.service_sms.service.SmsService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @program: yangbuyi_video
 * @ClassName: SmsController
 * @create: 2021-10-12 23:43
 * @author: Yang Shuai
 * @since： JDK1.8
 * @SmsController: $
 **/


@RestController
@RequestMapping("/service_sms/api/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    /**
     * 发送完短信后, 把生成的验证码写到redis
     */
    private final RedisTemplate<String, String> redisTemplate;

    /**
     * 根据手机号码发送验证码
     *
     * @param phone 电话
     * @return {@link ResponseResult}
     */
    @GetMapping(value = "/send/{phone}")
    public ResponseResult send (@PathVariable String phone) {
        // 校验手机号是否合法
        if (!PhoneUtils.isPhone(phone)) {
            throw new BusinessException("手机号码格式出错请重新输入!");
        }
        // 查看redis当中是否有当前手机对应的验证码   手机号当作key
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            //已经发送过了 还没有过期
            return ResponseResult.ok();
        }
        // 生成新的验证码
        code = randomCode();
        Map<String, Object> param = new HashMap<>();
        //把验证码写到参数当中云
        param.put("code", code);
        //发送短信
        boolean isSend = smsService.send(phone, param);
        //发送成功
        if (isSend) {
            //把验证码写到redis当中 五分钟过期时间
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return ResponseResult.ok();
        } else {
            //提示用户失败
            return ResponseResult.error().message("发送短信失败");
        }
    }


    /**
     * 生成4位的数字验证码
     * @return {@link String}
     */
    public String randomCode () {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int s = new Random().nextInt(10);
            sb.append(s);
        }
        return sb.toString();
    }


}