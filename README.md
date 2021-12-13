## video
本系统是一款类似于教学网站平台视频点播技术，课程功能作品观看一些类教学视频操作


## 平台简介

* 采用前后端分离的模式，微服务版本前端(使用vue-admin花裤衩模板)。
* 后端采用Spring Boot、Spring Cloud & Alibaba。
* 注册中心、配置中心选型Nacos，权限认证使用Redis。
* 流量控制框架选型Sentinel。
* 提供了技术栈（[Vue3](https://v3.cn.vuejs.org) [Element Plus](https://element-plus.org/zh-CN) [Vite](https://cn.vitejs.dev)）



## 系统模块

~~~
top.yangbuyi     
├── yangbuyi_video_portal    // 前端门户网站 [80]
├── yangbuyi_video_ui        // 前端 (后端管理) [81]
├── yangbuyi-gateway         // 网关模块 [9728]
├── yangbuyi-api             // 接口模块
│       └── api_pay                                      // 支付宝支付接口
│       └── api_user                                     // 用户接口
│       └── api_video                                    // 作品接口
│       └── api_vod                                      // 视频点播接口
│       └── api_websocket                                // 消息通讯接口
├── yangbuyi-common          // 通用模块
│       └── common_utils                                 // 工具类模块
│       └── service_base                                 // 业务配置模块
│       └── service_security                             // 认证模块
├── yangbuyi_service         // 业务模块
│       └── service_authority                            // 登录系统模块(后端)
│       └── service_user                                 // 登录系统模块(前端)
│       └── service_cms                                  // 门户系统模块
│       └── service_pay                                  // 支付系统模块
│       └── service_video                                // 作品系统模块
│       └── service_sms                                  // 信息发送模块
│       └── service_upload                               // 文件系统模块
│       └── service_vod                                  // 视频点播模块
│       └── service_websocket                            // 消息通讯模块
├──pom.xml                   // 公共依赖
~~~


## 内置功能

1.  用户管理：用户是系统操作者，该功能主要完成系统用户配置。
2.  菜单管理：配置系统菜单，操作权限，按钮权限标识等。
3.  角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
4.  字典管理：对系统中经常使用的一些较为固定的数据进行维护。
5.  参数管理：对系统动态配置常用参数。
6.  操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
7.  登录日志：系统登录日志记录查询包含登录异常。
8.  系统接口：根据业务代码自动生成相关的api接口文档。

## 演示图
....

## 开源微服务交流群

QQ群： 598347590
