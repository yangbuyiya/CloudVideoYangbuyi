/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_pay.common.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import top.yangbuyi.service_pay.common.properties.AliPayProperties;

/**
 * @author Yang Shuai
 * @date 2021/10/23
 */
@Configuration
public class AliPayApiConfig {
    /**
     * 阿里支付属性
     */
    @Autowired
    private AliPayProperties aliPayProperties;
    /**
     * json
     */
    private static final String JSON = "json";
    /**
     * rsa2 加密方式
     */
    private static final String RSA2 = "RSA2";
    /**
     * 字符集
     */
    private static final String CHARSET = "UTF-8";


    /**
     * 获取实例对象
     *
     * @return {@link AlipayClient}
     */
    public AlipayClient getAliPayClient(){
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(
                aliPayProperties.getServerUrl(),
                aliPayProperties.getAppId(),
                aliPayProperties.getPrivateKey(),
                JSON,
                CHARSET,
                aliPayProperties.getPublvicKey(),
                RSA2
        );
        return alipayClient;
    }
}