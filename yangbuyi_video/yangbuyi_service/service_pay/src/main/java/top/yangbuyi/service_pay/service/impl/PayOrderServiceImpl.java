/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_pay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yangbuyi.api_user.RemoteUserClient;
import top.yangbuyi.api_user.entity.User;
import top.yangbuyi.api_video.RemoteContentClient;
import top.yangbuyi.api_video.entity.vo.ContentWebVO;
import top.yangbuyi.common_utils.constant.OrderConstant;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.common_utils.utils.UUIDUtils;
import top.yangbuyi.service_base.exception.TransactionalException;
import top.yangbuyi.service_pay.entity.PayOrder;
import top.yangbuyi.service_pay.mapper.PayOrderMapper;
import top.yangbuyi.service_pay.service.PayOrderService;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author yangbuyiya
 * @since 2021-10-20
 */
@Service
@RequiredArgsConstructor
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements PayOrderService {

    private final RemoteContentClient remoteContentClient;
    private final RemoteUserClient remoteUserClient;

    /**
     * 创建用户订单模块
     */
    @Override
    @Transactional(rollbackFor = TransactionalException.class)
    public ResponseResult createOrderInfo (String contentId, String userIdByJwtToken) {
        // 获取用户信息
        User loginInfo = remoteUserClient.getLoginInfo(userIdByJwtToken);
        // 获取作品信息
        ContentWebVO contentWebVO = remoteContentClient.getContentInfoOrder(contentId);
        //创建Order对象，向order对象里面设置需要数据
        PayOrder order = new PayOrder();
        // 订单号
        order.setOrderNo(UUIDUtils.getRandomNumber());
        // 课程id
        order.setContentId(contentId);
        // 课程标题
        order.setContentTitle(contentWebVO.getTitle());
        // 课程封面
        order.setContentCover(contentWebVO.getCover());
        // 课程作者名称
        order.setAuthorName(contentWebVO.getAuthorName());
        // 课程价格
        order.setTotalFee(contentWebVO.getPrice());
        // 订单课程用户Id
        order.setUserId(userIdByJwtToken);
        // 用户手机
        order.setMobile(loginInfo.getMobile());
        // 用户昵称
        order.setNickname(loginInfo.getNickname());
        // 订单状态（0：未支付 1：已支付）
        order.setStatus(0);
        // 支付类型 ，支付宝2
        order.setPayType(2);
        baseMapper.insert(order);
        // 返回订单号
        return ResponseResult.ok().data(order.getOrderNo());
    }


    /**
     * 根据订单编号获取订单详情
     * @return
     */
    @Override
    public PayOrder getOrderInfoByOrderNo (String orderNo) {
        return baseMapper.selectOne(new LambdaQueryWrapper<PayOrder>().eq(PayOrder::getOrderNo, orderNo));
    }

    /**
     * 根据用户ID和作品ID查询是否购买改作品
     *
     * @return boolean
     */
    @Override
    public boolean getBuyContentStatus (String userId, String contentId) {
        // 订单状态是1表示支付成功
        int count = baseMapper.selectCount(new LambdaQueryWrapper<PayOrder>()
                                                   .eq(PayOrder::getUserId, userId)
                                                   .eq(PayOrder::getContentId, contentId)
                                                   .eq(PayOrder::getStatus, OrderConstant.STATUS_1));
        return count > 0;
    }

    /**
     * 根据用户ID获取拥有的订单信息
     *
     * @param userIdByJwtToken jwt用户id的令牌
     * @return {@link ResponseResult}
     */
    @Override
    public ResponseResult getOrdersByUserId (String userIdByJwtToken) {
        return ResponseResult.ok().data(baseMapper.selectList(new LambdaQueryWrapper<PayOrder>().eq(PayOrder::getUserId, userIdByJwtToken)));
    }

}
