/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import request from '@/utils/request'
export default {
  getPlayAuth(vid) {
    return request({
      url: `/service_vod/vod/getPlayAuth/${vid}`,
      method: 'get'
    })
  }
}
