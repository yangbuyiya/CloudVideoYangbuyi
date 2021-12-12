/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import top.yangbuyi.common_utils.utils.JwtUtils;
import top.yangbuyi.common_utils.utils.MD5;
import top.yangbuyi.service_base.controller.core.vo.LoginVo;
import top.yangbuyi.service_base.controller.core.vo.RegisterVo;
import top.yangbuyi.service_base.exception.BusinessException;
import top.yangbuyi.service_base.utils.text.StringUtils;
import top.yangbuyi.api_user.entity.User;
import top.yangbuyi.service_user.mapper.UserMapper;
import top.yangbuyi.service_user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yangbuyi
 * @since 2021-10-13
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public User getByOpenid (String openid) {
        return baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getOpenid, openid));
    }

    @Override
    public String login (LoginVo loginVo) {
        String phone = loginVo.getPhone();
        String password = loginVo.getPassword();
        //校验参数
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            throw new BusinessException("缺少参数");
        }
        //获取用户
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("mobile", phone));
        if (null == user) {
            throw new BusinessException("用户不存在");
        }
        //校验密码
        if (!MD5.encrypt(password).equals(user.getPassword())) {
            throw new BusinessException("密码错误");
        }
        //校验是否被禁用
        if (Boolean.TRUE.equals(user.getIsDisabled())) {
            throw new BusinessException("用户被禁用");
        }
        //使用JWT生成token字符串
        return JwtUtils.getJwtToken(user.getId(), user.getNickname());
    }

    @Override
    public void register (RegisterVo registerVo) {
        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String phone = registerVo.getPhone();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();
        //校验参数
        final boolean existed = (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code));
        if (existed)
            throw new BusinessException("缺少参数");
        //校验校验验证码
        //从redis获取发送的验证码
        String phoneCode = redisTemplate.opsForValue().get(phone);
        if (!code.equals(phoneCode))
            throw new BusinessException("验证码错误");
        //查询数据库中是否存在相同的手机号码
        Integer count = baseMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getMobile, phone));
        if (count > 0)
            throw new BusinessException("手机号已被使用");
        //添加注册信息到数据库
        User member = new User();
        member.setNickname(nickname);
        member.setMobile(registerVo.getPhone());
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("https://tse4-mm.cn.bing.net/th/id/OIP-C.F-pKmXxYK0lHIU6W41RSXgHaHY?pid=ImgDet&rs=1");
        this.save(member);
    }

}
