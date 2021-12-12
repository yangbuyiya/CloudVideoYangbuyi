/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

// 首页数据
import request from '@/utils/request'
export default {
  getList() {
    return request({
      url: `/service_video/indexPortal/getIndexData`,
      method: 'get'
    })
  }
}
