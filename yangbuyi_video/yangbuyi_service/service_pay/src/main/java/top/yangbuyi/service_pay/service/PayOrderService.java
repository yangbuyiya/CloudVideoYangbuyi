/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_pay.service;

import top.yangbuyi.api_user.entity.User;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.service_pay.entity.PayOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author yangbuyiya
 * @since 2021-10-20
 */
public interface PayOrderService extends IService<PayOrder> {

    /**
     * 创建用户订单模块
     *
     * @return {@link User}
     */
    ResponseResult createOrderInfo(String contentId, String userIdByJwtToken);

    /**
     * 根据订单编号获取订单详情
     * @return ResponseResult
     */
    PayOrder getOrderInfoByOrderNo(String orderNo);

    /**
     * 根据用户ID和作品ID查询是否购买改作品
     * @return boolean
     */
    boolean getBuyContentStatus(String userId, String contentId);

    /**
     * 根据用户ID获取拥有的订单信息
     *
     * @param userIdByJwtToken jwt用户id的令牌
     * @return {@link ResponseResult}
     */
    ResponseResult getOrdersByUserId (String userIdByJwtToken);
}
