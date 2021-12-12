/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import request from '@/utils/request'

const apiName = '/service_video/contentVideo'
export default {
  addContentVideo(params) {
    return request({
      url: `${apiName}`,
      method: 'post',
      data: params
    })
  },
  updateContentVideo(params) {
    return request({
      url: `${apiName}`,
      method: 'put',
      data: params
    })
  },
  getContentVideoById(params) {
    return request({
      url: `${apiName}/${params}`,
      method: 'get'
    })
  },
  deleteContentVideoById(id) {
    return request({
      url: `${apiName}/${id}`,
      method: 'delete'
    })
  }
}
