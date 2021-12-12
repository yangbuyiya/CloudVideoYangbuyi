/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import request from '@/utils/request'

const apiName = '/service_video/chapter'
export default {
  getNestedTreeList(contentId) {
    return request({
      url: `${apiName}/getChapterContentVideo/${contentId}`,
      method: 'get'
    })
  },
  insertChapter(params) {
    return request({
      url: `${apiName}`,
      method: 'post',
      data: params
    })
  },
  updateChapter(params) {
    return request({
      url: `${apiName}`,
      method: 'put',
      data: params
    })
  },
  deleteChapterById(id) {
    return request({
      url: `${apiName}/${id}`,
      method: 'delete'
    })
  },
  getContentOrVideoSortMax(type) {
    return request({
      url: `${apiName}/getContentOrVideoSortMax/${type}`,
      method: 'post'
    })
  }
}
