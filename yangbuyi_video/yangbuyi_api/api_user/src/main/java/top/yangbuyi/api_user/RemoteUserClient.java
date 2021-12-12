/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.api_user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.yangbuyi.api_user.entity.User;
import top.yangbuyi.api_user.factory.RemoteUserFactory;
import top.yangbuyi.common_utils.constant.CloudConstant;

/**
 * @program: yangbuyi_video
 * @ClassName: RemoteUserClient
 * @create: 2021/10/20:17:10
 * @author: Yang Shuai
 * @desc: api接口|
 **/
@FeignClient(value = CloudConstant.SERVICE_USER, fallbackFactory = RemoteUserFactory.class)
public interface RemoteUserClient {

    /**
     * 根据用户ID获取用户信息
     *
     * @param id
     * @return {@link User}
     */
    @GetMapping("/service_user/user/getUserInfoOrderById/{id}")
    public User getLoginInfo(@PathVariable String id);

}
