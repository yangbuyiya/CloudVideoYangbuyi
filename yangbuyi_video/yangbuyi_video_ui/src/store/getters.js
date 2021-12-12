/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  menus: state => state.user.menus,
  permissions: state => state.user.buttons,
  roles: state => state.user.roles
}
export default getters
