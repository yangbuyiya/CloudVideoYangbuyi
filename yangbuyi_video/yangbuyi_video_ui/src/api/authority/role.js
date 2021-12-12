/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import request from '@/utils/request'
const api_name = '/service_authority/admin/role'
export default {
  /** 获取分页列表数据*/
  getPageList(page, limit, searchObj) {
    return request({
      url: `${api_name}/getRolePageList/${page}/${limit}`,
      method: 'get',
      params: searchObj // url查询字符串或表单键值对
    })
  },
  /** 获取角色*/
  getById(id) {
    return request({
      url: `${api_name}/getRoleById/${id}`,
      method: 'get'
    })
  },
  /** 保存角色*/
  save(role) {
    return request({
      url: `${api_name}/saveRole`,
      method: 'post',
      data: role
    })
  },
  /** 更新角色*/
  updateById(role) {
    return request({
      url: `${api_name}/updateRole`,
      method: 'post',
      data: role
    })
  },
  /** 删除角色*/
  removeById(id) {
    return request({
      url: `${api_name}/removeRole/${id}`,
      method: 'post'
    })
  },
  /** 批量删除*/
  removeRows(idList) {
    return request({
      url: `${api_name}/batchRemoveRole`,
      method: 'post',
      data: idList
    })
  }

}
