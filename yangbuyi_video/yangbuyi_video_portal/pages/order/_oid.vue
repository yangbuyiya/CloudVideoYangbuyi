<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->

<template>
  <div class="order">
    <div class="um-order-ux-order-v2-ct-top">
      <div class="um-order-ux-order-v2-ct-top-title">
        <h1>确认订单信息</h1>
        <h4>请在2小时内完成支付，否则订单会被自动取消</h4>
      </div>

      <div class="um-order-ux-order-account f-cb">
        <div class="um-order-ux-order-account_avatar f-fl">
          <img :src="loginInfo.avatar">
        </div>
        <div class="um-order-ux-order-account_info f-fl">
          <div class="um-order-ux-order-account_info_nickName">购买帐号：{{ loginInfo.nickname }}</div>
          <!--Regular if16-->
          <div class="um-order-ux-order-account_info_tips">注意：购买后不支持退款、转让，请确认开课时间或有效期后再提交订单</div>
        </div>

      </div>
      <div class="um-order-ux-order-v2-pay-method">
        <div class="um-order-ux-order-v2-pay-method_head">支付方式</div>
        <div class="um-order-ux-order-v2-pay-method_body">
          <div class="order-v2-pay_current f-cb">
            <span class="order-v2-pay_current_icon current_icon_alipay f-fl"></span>
            <span class="order-v2-pay_current_type f-fl">支付宝</span>
          </div>
        </div>
      </div>


      <div class="um-order-ux-order-school-list">
        <div class="um-order-ux-order-school-list_title">购买课程</div>
        <div class="um-order-ux-order-school-list_list">
          <div class="um-order-ux-order-school">
            <div class="um-order-ux-order-school_courselist">
              <div class="um-order-ux-order-course">
                <div class="um-order-ux-order-course_card f-cb">
                  <div class="um-order-ux-order-course_card_img f-fl">
                    <a class="">
                      <img width="150px" height="80px" :src="order.contentCover">
                    </a>
                  </div>
                  <div class="um-order-ux-order-course_card_body f-fl">
                    <a class="title">
                      <span>{{ order.contentTitle }}</span>
                    </a>
                    <div class="time">永久可看</div>
                    <div class="promotion"></div>
                  </div>
                  <div class="um-order-ux-order-course_card_price f-fr">￥{{ order.totalFee }}</div>
                </div>
              </div>

            </div>

          </div>

        </div>
      </div>

    </div>
    <div class="um-order-ux-order-v2-ct-bottom um-order-ux-order-v2-ct-bottom_fixed" style="left: 471.5px;">
      <!--Regular if47-->
      <div class="um-order-ux-order-v2-go-pay f-cb f-pr">
        <form action="/pay/orderRedirectPage.htm" name="payForm">
          <input type="hidden" name="orderUrl">
        </form>
        <div class="um-order-ux-order-v2-go-pay_protocol f-pa">
          提交订单则表示您同意
          <span class="um-order-ux-order-v2-go-pay_protocol_btn">《疯码视频用户服务协议》</span>、
          <span class="um-order-ux-order-v2-go-pay_protocol_btn">《疯码视频售后政策》</span>等
        </div>
        <div class="um-order-ux-order-v2-go-pay_content f-fr">
          <div class="um-order-ux-order-v2-go-pay_content_price">
            <span class="v2-go-pay_content_price_title">实付:</span>
            <span class="v2-go-pay_content_price_account">
                <span class="v2-go-pay_content_price_account_icon">￥</span>{{ order.totalFee }}
            </span>
          </div>
          <a class="um-order-ux-order-v2-go-pay_content_btn" @click="payClick">立即支付</a>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import 'assets/css/order.css'
import cookie from "js-cookie";
import order from "@/api/order";

export default {
  layout: 'default',
  asyncData({params, error}) {
    return order.getOrdersInfo(params.oid).then(response => {
      return {
        order: response.data.rows
      }
    })
  },
  created() {
    this.showInfo();
  },
  data() {
    return {
      loginInfo: {
        avatar: '',
        nickname: ''
      }
    }
  },
  methods: {
    showInfo() {
      var jsonStr = cookie.get("portal_user");
      if (jsonStr) {
        this.loginInfo = JSON.parse(jsonStr)
      }
    },
    // 监听支付
    payClick() {
      order.aliPay(this.order.orderNo).then(response => {
        //支付的时候, 支付宝会给我们返回支付页面
        $('body').html(response.data.form);
      })
    }

  }
}
</script>
