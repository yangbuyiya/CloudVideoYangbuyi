/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_security.annotation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import top.yangbuyi.common_utils.config.ApplicationConfig;

import java.lang.annotation.*;

/**
 * 杨不易自定义注解
 *
 * @author Yang Shuai
 * @date 2021/10/23
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
// 指定要扫描的Mapper类的包的路径
@MapperScan("top.yangbuyi.**.mapper")
// 扫描包路径
@ComponentScan(basePackages = {"top.yangbuyi"})
// 开启服务发现/注册
// @EnableDiscoveryClient
// 开启线程异步执行
@EnableAsync
// 自动加载类
@Import({ApplicationConfig.class})
public @interface EnableCustomConfig
{

}
