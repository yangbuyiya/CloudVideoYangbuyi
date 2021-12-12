/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import { login, logout, getInfo, getMoveRouter } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    avatar: '',
    buttons: [],
    roles: [],
    menus: []
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_BUTTONS: (state, buttons) => {
    state.buttons = buttons
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  SET_MENU: (state, menus) => {
    state.menus = menus
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { data } = response
        commit('SET_TOKEN', data.token)
        setToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        console.log(response)
        const { data } = response
        console.log('role=' + data.roles)
        if (data.roles && data.roles.length > 0) { // 验证返回的roles是否是一个非空数组
          commit('SET_ROLES', data.roles)
        } else {
          reject(' 当前用户无角色信息请联系管理员设置，重新登陆 !')
        }
        const buttonAuthList = []
        data.permissionValueList.forEach(button => {
          buttonAuthList.push(button)
        })
        commit('SET_NAME', data.name)
        commit('SET_AVATAR', data.avatar)
        commit('SET_BUTTONS', buttonAuthList)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 用户路由
  getRouter({ commit, state }) {
    return new Promise((resolve, reject) => {
      getMoveRouter().then(response => {
        const menus = response.data.menuList
        menus.push({
          path: '/404',
          component: '404',
          hidden: true
        }, {
          path: '*',
          redirect: '/404',
          hidden: true
        })
        console.log('menus=' + menus)
        commit('SET_MENU', menus.reverse()) // 把name 保存到vuex中
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 用户登出
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        resetRouter() // 清空路由菜单
        commit('SET_TOKEN', '')// 清空前端vuex中存储的数据
        commit('SET_ROLES', [])// 清空前端vuex中存储的数据
        commit('SET_BUTTONS', [])
        commit('RESET_STATE')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

