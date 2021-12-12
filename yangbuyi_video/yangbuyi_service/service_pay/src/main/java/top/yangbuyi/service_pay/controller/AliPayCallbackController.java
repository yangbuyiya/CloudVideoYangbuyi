/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_pay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.yangbuyi.common_utils.utils.ServletUtils;
import top.yangbuyi.service_pay.common.properties.AliPayProperties;
import top.yangbuyi.service_pay.entity.PayOrder;
import top.yangbuyi.service_pay.service.PayLogService;
import top.yangbuyi.service_pay.service.PayOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: yangbuyi_video
 * @ClassName: AliPayCallbackController
 * @create: 2021/10/28:17:23
 * @author: Yang Shuai
 * @desc: |
 **/
@Controller
@RequestMapping("callback")
@RequiredArgsConstructor
public class AliPayCallbackController {
    /**
     * 支付日志
     */
    private final PayLogService payLogService;
    /**
     * 支付订单
     */
    private final PayOrderService payOrderService;
    /**
     * 支付宝配置
     */
    private final AliPayProperties aliPayProperties;


    /**
     * 只有付款成功才会跳转到这里且只跳转一次
     *
     * @return 回调地址
     */
    @GetMapping("/returnUrl")
    public String returnUrl() {
        try {
            HttpServletResponse response = ServletUtils.getResponse();
            HttpServletRequest request = ServletUtils.getRequest();
            response.setContentType("text/html;charset=" + StandardCharsets.UTF_8);
            PrintWriter out = response.getWriter();
            //获取支付宝GET过来反馈信息
            boolean signVerified = checkV1(request);
            //——请在这里编写您的程序（以下代码仅作参考）——
            if (signVerified) {
                //商户订单号
                String outTradeNo = new String(request.getParameter("out_trade_no")
                        .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                PayOrder payOrder = payOrderService.getOrderInfoByOrderNo(outTradeNo);
                String contentId = payOrder.getContentId();
                String url = "http://localhost:3000/video/" + contentId;
                return "redirect:" + url;
            } else {
                out.println("验签回调失败");
                return null;
            }
        } catch (IOException | AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 服务器通知 :
     * 1. ip地址一定是公网的，私有地址支付宝无法通知到客户端
     * 2. 请求方式是POST请求
     * 3. 程序流程完毕需要返回success给到支付宝
     */
    @PostMapping("/notifyUrl")
    public void notifyUrl() {
        HttpServletResponse response = ServletUtils.getResponse();
        HttpServletRequest request = ServletUtils.getRequest();
        try {
            PrintWriter out = response.getWriter();
            boolean signVerified = checkV1(request);
            if (signVerified) {//验证成功
                System.out.println("异步验证成功");
                //商户订单号
                String outTradeNo = new String(request.getParameter("out_trade_no")
                        .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                //支付宝交易号
                String transactionId = new String(request.getParameter("trade_no")
                        .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                //交易状态
                String tradeStatus = new String(request.getParameter("trade_status")
                        .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                HashMap<String, String> map = new HashMap<>();
                map.put("out_trade_no", outTradeNo);
                map.put("trade_state", tradeStatus);
                map.put("transaction_id", transactionId);
                if (tradeStatus.equals("TRADE_FINISHED")) {
                    // 判断该笔订单是否在商户网站中已经做过处理
                    // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                    // 并执行商户的业务程序
                    // 如果有做过处理，不执行商户的业务程序
                    System.out.println("订单已完成");
                    PayOrder payOrder = payOrderService.getOrderInfoByOrderNo(outTradeNo);
                    if (payOrder.getStatus() != 1) {
                        // 更新订单状态
                        payLogService.updateOrdersStatus(map);
                    }
                } else if (tradeStatus.equals("TRADE_SUCCESS")) {
                    // 判断该笔订单是否在商户网站中已经做过处理
                    // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                    // 并执行商户的业务程序
                    // 如果有做过处理，不执行商户的业务程序
                    // 我们的业务代码
                    System.out.println("订单付款成功");
                    payLogService.updateOrdersStatus(map);
                }
                out.println("success");
            } else {
                //验证失败
                out.println("fail");
            }
        } catch (IOException | AlipayApiException e) {
            e.printStackTrace();
        }
    }


    /***
     * 校验签名
     * rsa2 验签
     * */
    private boolean checkV1(HttpServletRequest request) throws AlipayApiException {
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return AlipaySignature.rsaCheckV1(
                params,
                aliPayProperties.getPublvicKey(),
                "UTF-8",
                "RSA2"
        );
    }

}
