/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import request from '@/utils/request'

const api_name = '/service_vod/vod'

export default {
  deleteVodById(id) {
    return request({
      url: `${api_name}/delete-vod/${id}`,
      method: 'post'
    })
  }
}
