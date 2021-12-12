/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_pay.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里支付属性
 *
 * @author Yang Shuai
 * @date 2021/10/23
 */
@Data
@Component
@ConfigurationProperties(prefix = "pay.alibaba")
public class AliPayProperties {
    /**
     * 应用程序id
     */
    private String appId;
    /**
     * 私钥
     */
    private String privateKey;
    /**
     * 公钥
     */
    private String publvicKey;
    /**
     * 支付宝服务地址
     */
    private String serverUrl;
    /**
     * 回调地址
     */
    private String returnUrl;
    /**
     * 异步回调
     */
    private String notyfyUrl;
}
