/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_authority.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yangbuyi.service_authority.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author yangbuyi
 * @since 2021-09-27
 */
public interface UserService extends IService<User> {

    void selectPage (Page<User> pageParam, User userQueryVo);

    User selectByUsername (String username);
}
