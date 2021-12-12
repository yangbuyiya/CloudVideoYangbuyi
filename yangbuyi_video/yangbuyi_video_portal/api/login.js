/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import request from '@/utils/request'

export default {
  /**
   * 登录
   * @param userInfo
   * @returns {*}
   */
  login(userInfo) {
    return request({
      url: `/service_user/user/login`,
      method: 'post',
      data: userInfo
    })
  },
  /**
   * 根据token获取用户信息
   * @returns {*}
   */
  getLoginInfo() {
    return request({
      url: `/service_user/user/auth/getLoginInfo`,
      method: 'get',
    })
  },
  /**
   * 验证是否已经登录
   * @returns {*}
   */
  checkLogin(){
    return request({
      url: `/service_user/user/wx/checkLogin`,
      method: 'get',
    })
  }
}
