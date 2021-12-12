/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import Layout from '@/layout'

/**
 * 常量路由-》所有人都可以访问
 */
export const constantRoutes =
  [
    {
      path: '/login',
      component: () => import('@/views/login/index'),
      hidden: true
    },
    {
      path: '/',
      component: Layout,
      children: [{
        path: '/',
        name: '首页',
        component: () => import('@/views/dashboard/index'),
        meta: { title: '首页', icon: 'dashboard' }
      }]
    },
    {
      path: '/websocket',
      component: Layout,
      redirect: '/websocket/websocketList',
      name: 'websocket',
      meta: { title: '通讯', icon: 'el-icon-s-help' },
      children: [
        {
          path: 'websocketList',
          name: 'WebsocketList',
          component: () => import('@/views/service/websocket'),
          meta: { title: '通讯1', icon: 'table' }
        }
      ]
    }
  ]
/*

  [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },
  {
    path: '/author',
    component: Layout,
    redirect: '/author/authorList',
    name: 'Author',
    meta: { title: '创作者', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'authorList',
        name: 'AuthorList',
        component: () => import('@/views/service/author/list'),
        meta: { title: '作者列表', icon: 'table' }
      },
      {
        path: 'authorSave',
        name: 'AuthorSave',
        component: () => import('@/views/service/author/save'),
        meta: { title: '添加作者', icon: 'tree' }
      },
      {
        path: 'authorEdit/:id',
        name: 'AuthorEdit',
        component: () => import('@/views/service/author/save'),
        meta: { title: '编辑', icon: 'tree' },
        hidden: true
      }
    ]
  },
  {
    path: '/category',
    component: Layout,
    redirect: '/category/categoryList',
    name: 'Category',
    meta: { title: '视频分类管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'categoryList',
        name: 'CategoryList',
        component: () => import('@/views/service/category/list'),
        meta: { title: '分类列表', icon: 'table' }
      },
      {
        path: 'categorySave',
        name: 'CategorySave',
        component: () => import('@/views/service/category/save'),
        meta: { title: '添加视频分类', icon: 'tree' }
      }
    ]
  },
  {
    path: '/content',
    component: Layout,
    redirect: '/content/list',
    name: '作者',
    meta: { title: '作品管理', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'list',
        name: '作品列表',
        component: () => import('@/views/service/content/list'),
        meta: { title: '作品列表', icon: 'table' }
      },
      {
        path: 'info/:id',
        name: '添加作品',
        component: () => import('@/views/service/content/info'),
        meta: { title: '添加作品', icon: 'tree' }
      },
      {
        path: 'chapter/:id',
        name: '章节信息',
        component: () => import('@/views/service/content/chapter'),
        meta: { title: '章节信息', icon: 'tree' },
        hidden: true
      },
      {
        path: 'send/:id',
        name: '最终发布',
        component: () => import('@/views/service/content/send'),
        meta: { title: '最终发布', icon: 'tree' },
        hidden: true
      }
    ]
  },
  {
    path: '/authority',
    component: Layout,
    redirect: '/authority/user/list',
    name: '权限管理',
    meta: { title: '权限管理', icon: 'tree' },
    children: [
      {
        path: 'user/list',
        name: '用户管理',
        component: () => import('@/views/authority/user/list'),
        meta: { title: '用户管理' }
      },
      {
        path: 'user/add',
        name: '用户添加',
        component: () => import('@/views/authority/user/form'),
        meta: { title: '用户添加' },
        hidden: true
      },
      {
        path: 'user/update/:id',
        name: '用户修改',
        component: () => import('@/views/authority/user/form'),
        meta: { title: '用户修改' },
        hidden: true
      },
      {
        path: 'user/role/:id',
        name: '用户角色',
        component: () => import('@/views/authority/user/roleForm'),
        meta: { title: '用户角色' },
        hidden: true
      },
      {
        path: 'role/list',
        name: '角色管理',
        component: () => import('@/views/authority/role/list'),
        meta: { title: '角色管理' }
      },
      {
        path: 'role/form',
        name: '角色添加',
        component: () => import('@/views/authority/role/form'),
        meta: { title: '角色添加' },
        hidden: true
      },
      {
        path: 'role/update/:id',
        name: '角色修改',
        component: () => import('@/views/authority/role/form'),
        meta: { title: '角色修改' },
        hidden: true
      },
      {
        path: 'role/distribution/:id',
        name: '角色权限',
        component: () => import('@/views/authority/role/roleForm'),
        meta: { title: '角色权限' },
        hidden: true
      },
      {
        path: 'menu/list',
        name: '菜单管理',
        component: () => import('@/views/authority/menu/list'),
        meta: { title: '菜单管理' }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]
* */

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
