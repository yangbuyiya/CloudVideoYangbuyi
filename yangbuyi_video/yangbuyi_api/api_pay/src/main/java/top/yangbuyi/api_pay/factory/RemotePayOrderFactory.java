/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.api_pay.factory;

import feign.hystrix.FallbackFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import top.yangbuyi.api_pay.RemotePayOrderClient;

/**
 * @program: yangbuyi_video
 * @ClassName: RemotePayOrderFactory
 * @create: 2021/10/21:18:10
 * @author: Yang Shuai
 * @desc: |
 **/
@Component
public class RemotePayOrderFactory implements FallbackFactory<RemotePayOrderClient> {
    private static final Logger logger = LogManager.getLogger(RemotePayOrderFactory.class);

    @Override
    public RemotePayOrderClient create(Throwable cause) {
        logger.error("支付模块请求失败:{}", cause.getLocalizedMessage());
        return new RemotePayOrderClient() {
            @Override
            public boolean getBuyContent (String userId, String contentId) {
                return false;
            }
        };
    }
}
