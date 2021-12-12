/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.api_user.factory;


import feign.hystrix.FallbackFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import top.yangbuyi.api_user.RemoteUserClient;
import top.yangbuyi.api_user.entity.User;

/**
 * @program: yangbuyi_video
 * @ClassName: UserFactory
 * @create: 2021/10/20:17:11
 * @author: Yang Shuai
 * @desc: |
 **/
@Component
public class RemoteUserFactory implements FallbackFactory<RemoteUserClient> {
    private static final Logger logger = LogManager.getLogger(RemoteUserFactory.class);


    @Override
    public RemoteUserClient create (Throwable cause) {
        logger.error("门户用户模块错误:{}", cause.getLocalizedMessage());
        return new RemoteUserClient() {
            @Override
            public User getLoginInfo (String id) {
                return null;
            }
        };
    }
}
