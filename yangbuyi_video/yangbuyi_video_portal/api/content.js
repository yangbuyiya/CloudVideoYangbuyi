/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import request from '@/utils/request'


export default {
  getPageList(query) {
    return request({
      url: `/service_video/content/getContentListPage`,
      method: 'get',
      params: query
    })
  },
  //查询所有分类的方法
  getAllCategory() {
    return request({
      url: '/service_video/category/getAllCategory',
      method: 'get'
    })
  },
  // 根据作品id获取作品详情信息
  geContentDetailById(contentId, app) {
    return request({
      url: `/service_video/content/getContentDetailByContentId/${contentId}`,
      method: 'get',
      headers: {
        'token': app.$cookies.get("login_token") || ''
      }
    })
  }
}



