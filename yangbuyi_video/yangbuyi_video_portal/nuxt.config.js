/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

import * as webpack from "webpack";

module.exports = {
  /*
  ** Headers of the page
  */
  head: {
    title: '易课堂门户系统',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      { hid: 'description', name: 'description', content: '门户系统' }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' }
    ]
  },
  /*
  ** Customize the progress bar color
  */
  loading: { color: '#3B8070' },
  /*
  ** Build configuration
  */
  build: {
    /*
    ** Run ESLint on save
    */
    extend (config, { isDev, isClient }) {
      if (isDev && isClient) {
        config.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /(node_modules)/
        })
      }
    },
    vendor: ['element-ui'],
    // 插件导入
    plugins: [
      new webpack.ProvidePlugin({
        '$' : 'jquery'
      })
    ]
  },
  // 插件集成
  /*
  * ssr: 是否服务端渲染
  * */
  plugins: [
    { src: '~/plugins/nuxt-swiper-plugin.js', ssr: true },
    { src: '~plugins/ElementUI', ssr: true }
  ],
  /* 样式 */
  css: [
    'swiper/css/swiper.css',
    'element-ui/lib/theme-chalk/index.css'
  ],
  modules: [
    'cookie-universal-nuxt', ['cookie-universal-nuxt', { parseJSON: true }],
  ],
}

