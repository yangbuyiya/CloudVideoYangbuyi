## 平台简介

* 采用前后端分离的模式，微服务版本前端(使用vue-admin花裤衩模板)。
* 后端采用Spring Boot、Spring Cloud & Alibaba。
* 注册中心、配置中心选型Nacos，权限认证使用Redis。
* 流量控制框架选型Sentinel。
* 提供了技术栈（[Vue3](https://v3.cn.vuejs.org) [Element Plus](https://element-plus.org/zh-CN) [Vite](https://cn.vitejs.dev)）


## 开源微服务交流群

QQ群： 598347590

## 基本操作文档
[文档](https://www.yangbuyi.top/archives/yiketang)

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

1. 用户管理：用户是系统操作者，该功能主要完成系统用户配置。
2. 菜单管理：配置系统菜单，操作权限，按钮权限标识等。
3. 角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
4. 字典管理：对系统中经常使用的一些较为固定的数据进行维护。
5. 参数管理：对系统动态配置常用参数。
6. 操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
7. 登录日志：系统登录日志记录查询包含登录异常。
8. 系统接口：根据业务代码自动生成相关的api接口文档。
9. 作品管理：创作者的作品教学视频等。
10. 创作者管理： 创作者用户信息。
11. 焦点图管理： 门户网站页面轮播图
12. 作品分类管理：门户分类作品管理。
13. 微信登录
14. 短信登录
15. 沙箱支付宝

## 演示图

### 后端页面

#### 后端首页

![后端首页](yangbuyi_video/images/img.png)

#### 门户焦点图管理

![门户焦点图管理](yangbuyi_video/images/img_1.png)

#### 门户页面-列表

![门户作品管理-列表](yangbuyi_video/images/img_2.png)

#### 门户页面-编辑

![门户作品管理-编辑](yangbuyi_video/images/img_3.png)

#### 门户作品分类管理-列表

![门户作品分类管理-列表](yangbuyi_video/images/img_4.png)

#### 门户作品分类管理-上传分类

![门户作品分类管理-上传分类](yangbuyi_video/images/img_5.png)

#### 门户创作者管理-列表

![门户创作者管理-列表](yangbuyi_video/images/img_6.png)

#### 门户创作者管理-编辑

![门户创作者管理-编辑](yangbuyi_video/images/img_7.png)

#### 后端用户管理-列表

![后端用户管理-列表](yangbuyi_video/images/img_8.png)

#### 后端角色管理-列表

![后端角色管理-列表](yangbuyi_video/images/img_9.png)

#### 后端菜单管理-列表

![后端菜单管理-列表](yangbuyi_video/images/img_10.png)

### 前端门户

#### 门户页面-首页

![门户页面-首页](yangbuyi_video/images/img_11.png)

#### 门户页面-全部视频

![门户页面-全部视频](yangbuyi_video/images/img_12.png)

#### 门户页面-创作者

![门户页面-创作者](yangbuyi_video/images/img_13.png)

#### 门户页面-视频详情购买页

![门户页面-视频详情购买页](yangbuyi_video/images/img_14.png)

#### 门户页面-订单页面

![门户页面-订单页面](yangbuyi_video/images/img_15.png)

#### 门户页面-支付页面1

![门户页面-支付页面1](yangbuyi_video/images/img_16.png)

#### 门户页面-支付页面2

![门户页面-支付页面2](yangbuyi_video/images/img_17.png)

#### 门户页面-支付页面3

![门户页面-支付页面3](yangbuyi_video/images/img_18.png)

#### 门户页面-支付页面4（购买成功）

![门户页面-支付页面4（购买成功）](yangbuyi_video/images/img_19.png)

#### 门户页面-立即学习页

![门户页面-立即学习页](yangbuyi_video/images/img_20.png)

