/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.api_pay;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.yangbuyi.api_pay.factory.RemotePayOrderFactory;
import top.yangbuyi.common_utils.constant.CloudConstant;
import top.yangbuyi.common_utils.utils.ResponseResult;

/**
 * @program: yangbuyi_video
 * @ClassName: RemotePayOrderClient
 * @create: 2021/10/21:18:10
 * @author: Yang Shuai
 * @desc: api接口|
 **/
@FeignClient(value = CloudConstant.SERVICE_PAY, fallbackFactory = RemotePayOrderFactory.class)
public interface RemotePayOrderClient {

    /**
     * 得到购买内容
     * 根据用户ID和作品ID查询是否购买改作品
     *
     * @param userId    用户id
     * @param contentId 内容识别
     * @return boolean
     */
    @GetMapping("/service_pay/pay-order/getBuyContent/{userId}/{contentId}")
    public boolean getBuyContent(@PathVariable @Param("userId") String userId, @PathVariable @Param("contentId") String contentId);
}
