/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import request from '@/utils/request'

export default {
  // 焦点图列表
  getBannerPageList(params) {
    return request({
      url: `/service_cms/banner/pageBanner/${params.page}/${params.limit}`,
      method: 'post',
      data: params
    })
  },
  // 新增焦点图
  addBanner(params) {
    return request({
      url: `/service_cms/banner/addBanner`,
      method: 'post',
      data: params
    })
  },
  // 修改焦点图
  updateBanner(params) {
    return request({
      url: `/service_cms/banner/update`,
      method: 'put',
      data: params
    })
  },
  // 删除焦点图
  removeBanner(params) {
    return request({
      url: `/service_cms/banner/remove/${params}`,
      method: 'delete'
    })
  },
  // 根据ID获取焦点图
  getBannerById(params) {
    return request({
      url: `/service_cms/banner/get/${params}`,
      method: 'get'
    })
  }
}
