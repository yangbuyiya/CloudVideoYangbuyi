/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yangbuyi.service_authority.entity.User;
import top.yangbuyi.service_authority.mapper.UserMapper;
import top.yangbuyi.service_authority.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yangbuyi.service_base.utils.text.StringUtils;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yangbuyi
 * @since 2021-09-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public void selectPage (Page<User> pageParam, User userQueryVo) {
        final LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(userQueryVo.getUsername()), User::getUsername, userQueryVo.getUsername());
        baseMapper.selectPage(pageParam, wrapper);
    }

    @Override
    public User selectByUsername (String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(StringUtils.isNotBlank(username), User::getUsername, username));
    }
}
