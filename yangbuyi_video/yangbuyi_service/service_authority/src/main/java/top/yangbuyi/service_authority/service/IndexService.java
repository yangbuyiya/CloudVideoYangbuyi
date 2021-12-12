/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_authority.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;
import java.util.Map;

/**
 * @program: yangbuyi_video
 * @ClassName: IndexService
 * @create: 2021-10-03 21:36
 * @author: Yang Shuai
 * @since： JDK1.8
 * @IndexService: $
 **/
public interface IndexService {

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return {@link Map<String, Object>}
     */
    Map<String, Object> getUserInfo (String username);

    /**
     * 根据用户名称查询菜单
     *
     * @param username 用户名
     * @return {@link List<JSONObject>}
     */
    List<JSONObject> getMenuByUserName (String username);
}
