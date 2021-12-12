/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import hasRole from './hasRole'
import hasPermi from './hasPermi'

/**
 * 设置属性名称
 * @param Vue
 */
const install = function(Vue) {
  Vue.directive('hasRole', hasRole)
  Vue.directive('hasPer', hasPermi)
}

if (window.Vue) {
  window['hasRole'] = hasRole
  window['hasPer'] = hasPermi
  Vue.use(install); // eslint-disable-line
}

export default install
