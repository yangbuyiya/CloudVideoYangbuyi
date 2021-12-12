/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_pay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yangbuyi.common_utils.utils.ServletUtils;
import top.yangbuyi.service_base.exception.BusinessException;
import top.yangbuyi.service_pay.common.config.AliPayApiConfig;
import top.yangbuyi.service_pay.common.properties.AliPayProperties;
import top.yangbuyi.service_pay.entity.PayLog;
import top.yangbuyi.service_pay.entity.PayOrder;
import top.yangbuyi.service_pay.mapper.PayLogMapper;
import top.yangbuyi.service_pay.service.PayLogService;
import top.yangbuyi.service_pay.service.PayOrderService;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author yangbuyiya
 * @since 2021-10-20
 */
@Service
@RequiredArgsConstructor
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 编码
     */
    private static final String CHARSET = "UTF-8";

    /**
     * 交易多长时间后关闭
     */
    private static final String EXPIRE = "30m";

    private final AliPayProperties aliPayProperties;
    private final AliPayApiConfig aliPayApiConfig;
    private final PayOrderService payOrderService;


    /**
     * 电脑网站支付宝支付
     */
    @Override
    public String aliPay(String orderNo) {
        //1 根据订单号查询订单信息
        QueryWrapper<PayOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);
        PayOrder order = payOrderService.getOne(wrapper);
        //支付内容参数
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(order.getOrderNo());
        //销售产品码 电脑网站支付固定为 FAST_INSTANT_TRADE_PAY
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        //商品金额
        model.setTotalAmount(order.getTotalFee().toString());
        //商品标题
        model.setSubject(order.getContentTitle());
        //商品描述
        model.setBody("支付宝支付，共" + order.getTotalFee() + "元");
        //设置订单最晚付款时间  过期交易将关闭
        model.setTimeoutExpress(EXPIRE);
        //构建请求
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        // 同步回调地址
        request.setReturnUrl(aliPayProperties.getReturnUrl());
        // 异步回调地址
        request.setNotifyUrl(aliPayProperties.getNotyfyUrl());
        request.setBizModel(model);
        //发起请求
        try {
            String form = aliPayApiConfig.getAliPayClient().pageExecute(request).getBody();
            HttpServletResponse response = ServletUtils.getResponse();
            response.setContentType("text/html;charset=" + CHARSET);
            return form;
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new BusinessException("支付宝电脑网站支付失败");
        }
    }

    @Override
    @Transactional
    public boolean updateOrdersStatus(Map<String, String> map) {
        return this.payOrderService.update(new PayOrder().setStatus(1), new LambdaQueryWrapper<PayOrder>()
                .eq(PayOrder::getOrderNo, String.valueOf(map.get("out_trade_no"))));
    }
}
