/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_user.service;

import top.yangbuyi.service_base.controller.core.vo.LoginVo;
import top.yangbuyi.service_base.controller.core.vo.RegisterVo;
import top.yangbuyi.api_user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author yangbuyi
 * @since 2021-10-13
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     *
     * @param loginVo 登录签证官
     * @return 返回登录token
     */
    String login(LoginVo loginVo);

    /**
     * 用户注册
     * @param registerVo
     */
    void register(RegisterVo registerVo);

    /**
     * 根据openId查询用户
     *
     * @param openId 开放id
     * @return {@link User}
     */
    User getByOpenid(String openId);
}
