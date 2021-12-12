/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import request from '@/utils/request'

export default {
  //根据手机号发验证码
  sendCode(phone) {
    return request({
      url: `/service_sms/api/sms/send/${phone}`,
      method: 'get'
    })
  },
  //注册的方法
  register(formItem) {
    return request({
      url: `/service_user/user/register`,
      method: 'post',
      data: formItem
    })
  }
}

