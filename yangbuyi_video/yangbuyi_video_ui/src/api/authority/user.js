/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import request from '@/utils/request'
const api_name = '/service_authority/admin/user'
export default {
  /** 获取用户分页数据*/
  getPageList(page, limit, searchObj) {
    return request({
      url: `${api_name}/getUserList/${page}/${limit}`,
      method: 'get',
      params: searchObj // url查询字符串或表单键值对
    })
  },
  /** 根据用户id查询用户*/
  getUserById(id) {
    return request({
      url: `${api_name}/getUserById/${id}`,
      method: 'get'
    })
  },
  /** 保存用户*/
  save(user) {
    return request({
      url: `${api_name}/saveUser`,
      method: 'post',
      data: user
    })
  },
  /** 更新用户*/
  updateById(user) {
    return request({
      url: `${api_name}/updateUser`,
      method: 'post',
      data: user
    })
  },
  /** 根据用户id查询用户下角色信息*/
  getAssign(userId) {
    return request({
      url: `${api_name}/getUserRoleData/${userId}`,
      method: 'get'
    })
  },
  /** 保存用户角色信息*/
  saveAssign(userId, roleId) {
    return request({
      url: `${api_name}/doAssignRole`,
      method: 'post',
      params: { userId, roleId }
    })
  },
  /** 删除用户*/
  removeById(id) {
    return request({
      url: `${api_name}/removeUser/${id}`,
      method: 'post'
    })
  },
  /** 批量删除用户*/
  removeRows(idList) {
    return request({
      url: `${api_name}/batchRemoveUser`,
      method: 'post',
      data: idList
    })
  }
}
