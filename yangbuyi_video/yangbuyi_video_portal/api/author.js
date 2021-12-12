/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import request from '@/utils/request'
const api_name = '/service_video/author'

export default {
  authorWebList(page, limit) {
    return request({
      url: `${api_name}/pageList/${page}/${limit}`,
      method: 'post'
    })
  },
  getAuthorById(authorId) {
    return request({
      url: `${api_name}/getAuthorWithId/${authorId}`,
      method: 'get'
    })
  }

}
