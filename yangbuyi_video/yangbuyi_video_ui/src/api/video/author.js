/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import request from '@/utils/request'

export default {
  // 1. 作者列表
  getAuthorPageList(params) {
    return request({
      url: `/service_video/author/pageList/${params.page}/${params.limit}`,
      method: 'post',
      params
    })
  },
  // 2.删除作者
  deleteAuthorWithId(id) {
    return request({
      url: `/service_video/author/deleteAuthor/${id}/`, // 路由参数拼接
      method: 'post'
    })
  },
  // 3.添加
  addAuthor(author) {
    return request({
      url: `/service_video/author/addAuthor`, // 路由参数拼接
      method: 'post',
      /* data会自动转成json传递到接口当中*/
      data: author
    })
  },
  // 4.根据id查询作者
  getOneAuthor(id) {
    return request({
      url: `/service_video/author/getAuthorWithId/${id}`, // 路由参数拼接
      method: 'get'
    })
  },
  // 5.更新作者
  updateAuthor(author) {
    return request({
      url: `/service_video/author/updateAuthor`, // 路由参数拼接
      method: 'post',
      /* data会自动转成json传递到接口当中*/
      data: author
    })
  }

}
