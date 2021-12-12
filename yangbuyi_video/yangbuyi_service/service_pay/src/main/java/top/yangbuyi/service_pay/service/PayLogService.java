/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yangbuyi.service_pay.entity.PayLog;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author yangbuyiya
 * @since 2021-10-20
 */
public interface PayLogService extends IService<PayLog> {

    //阿里电脑网站支付
    String aliPay(String orderId);
    //更新订单状态
    boolean updateOrdersStatus(Map<String, String> map);
}
