/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.api_video;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.yangbuyi.api_video.entity.vo.ContentWebVO;
import top.yangbuyi.api_video.factory.RemoteContentClientFactory;
import top.yangbuyi.common_utils.constant.CloudConstant;

/**
 * @program: yangbuyi_video
 * @ClassName: RemoteContentClient
 * @create: 2021/10/20:17:10
 * @author: Yang Shuai
 * @desc: api接口|
 **/
@FeignClient(value = CloudConstant.SERVICE_VIDEO, fallbackFactory = RemoteContentClientFactory.class)
public interface RemoteContentClient {

    /**
     * 根据作品id查询作品信息
     */
    @GetMapping("/service_video/content/getContentInfoOrder/{id}")
    public ContentWebVO getContentInfoOrder(@PathVariable("id") String id);

}
