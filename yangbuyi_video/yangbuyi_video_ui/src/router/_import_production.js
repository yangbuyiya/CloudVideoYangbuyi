/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

// 生产环境导入组件
module.exports = file => () => import('@/views/' + file + '.vue')
