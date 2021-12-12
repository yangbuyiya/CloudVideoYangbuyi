/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import axios from 'axios'
import {MessageBox, Message} from 'element-ui'
import Cookies from 'js-cookie'


// 创建请求实例
const service = axios.create({
  baseURL: 'http://localhost:9000',
  timeout: 500000 // request timeout
})

// 响应信息
service.interceptors.response.use(
  response => {
    const res = response.data

    // if the custom code is not 20000, it is judged as an error.
    if (res.code !== 20000) {
      Message({
        message: res.message || 'Error',
        type: 'error',
        duration: 5 * 1000
      })

      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        // to re-login
        MessageBox.confirm('You have been logged out, you can cancel to stay on this page, or log in again', 'Confirm logout', {
          confirmButtonText: 'Re-Login',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.log("错误",error);
    Message({
      message: error,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

// 请求带入头部信息
service.interceptors.request.use(
  config => {
    // 获取token
    if (Cookies.get('login_token')) {
      config.headers['token'] = Cookies.get('login_token');
    }
    return config
  },
  err => {
    return Promise.reject(err);
  })
export default service
