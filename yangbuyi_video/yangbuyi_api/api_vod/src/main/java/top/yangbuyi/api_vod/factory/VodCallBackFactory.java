/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.api_vod.factory;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.yangbuyi.api_vod.VodClient;
import top.yangbuyi.common_utils.utils.ResponseResult;

/**
 * @program: yangbuyi_video
 * @ClassName: VodCallBackFactory
 * @create: 2021-09-25 00:06
 * @author: Yang Shuai
 * @since： JDK1.8
 * @VodFactory: 熔断降级‘$
 **/

@Component
public class VodCallBackFactory implements FallbackFactory<VodClient> {
    private static final Logger log = LoggerFactory.getLogger(VodCallBackFactory.class);

    @Override
    public VodClient create(Throwable cause) {
        log.error("终端检测接口服务调用失败:{}", cause.getMessage());
        return new VodClient() {
            @Override
            public ResponseResult removeVideo(String videoId) {
                return ResponseResult.error().data("远程视频删除失败!");
            }

            @Override
            public ResponseResult testVideo(String videoId) {
                return ResponseResult.error().data("远程视频删除失败!");
            }
        };
    }


}
