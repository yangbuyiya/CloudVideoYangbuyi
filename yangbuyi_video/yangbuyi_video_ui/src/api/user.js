/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/service_authority/admin/index/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/service_authority/admin/index/info',
    method: 'get',
    params: token
  })
}

export function getMoveRouter() {
  return request({
    url: '/service_authority/admin/index/menu',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/service_authority/admin/index/logout',
    method: 'post'
  })
}
