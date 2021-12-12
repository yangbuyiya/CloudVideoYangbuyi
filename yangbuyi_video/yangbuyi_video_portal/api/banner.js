/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import request from '@/utils/request'
export default {
  getList() {
    return request({
      url: `/service_cms/banner/getAllBanner`,
      method: 'get'
    })
  }
}
