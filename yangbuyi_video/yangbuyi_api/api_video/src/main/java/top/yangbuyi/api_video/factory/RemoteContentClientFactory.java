/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.api_video.factory;

import feign.hystrix.FallbackFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import top.yangbuyi.api_video.RemoteContentClient;
import top.yangbuyi.api_video.entity.vo.ContentWebVO;

/**
 * @program: yangbuyi_video
 * @ClassName: UserFactory
 * @create: 2021/10/20:17:11
 * @author: Yang Shuai
 * @desc: |
 **/
@Component
public class RemoteContentClientFactory implements FallbackFactory<RemoteContentClient> {
    private static final Logger logger = LogManager.getLogger(RemoteContentClientFactory.class);


    @Override
    public RemoteContentClient create (Throwable cause) {
        logger.error("作品模块错误:{}", cause.getLocalizedMessage());
        return new RemoteContentClient() {
            @Override
            public ContentWebVO getContentInfoOrder (String id) {
                return null;
            }
        };
    }
}
