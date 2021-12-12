/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.api_vod;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import top.yangbuyi.api_vod.factory.VodCallBackFactory;
import top.yangbuyi.common_utils.constant.CloudConstant;
import top.yangbuyi.common_utils.utils.ResponseResult;

/**
 * @program: yangbuyi_video
 * @ClassName: VodClient
 * @create: 2021-09-24 23:42
 * @author: Yang Shuai
 * @since： JDK1.8
 * @VodClient: 视频点播接口$
 **/
@FeignClient(name = CloudConstant.SERVICE_VOD, fallbackFactory = VodCallBackFactory.class)
public interface VodClient {

    /**
     * 删除视频
     *
     * @param videoId 视频id
     * @return {@link ResponseResult}
     */
    @PostMapping("/service_vod/vod/delete-vod/{videoId}")
    public ResponseResult removeVideo (@PathVariable @Param("videoId") String videoId);

    /**
     * 测试远程调用是否联通接口
     * @param videoId
     * @return
     */
    @GetMapping("/service_vod/vod/test-vod/{videoId}")
    public ResponseResult testVideo (@PathVariable @Param("videoId") String videoId);

}
