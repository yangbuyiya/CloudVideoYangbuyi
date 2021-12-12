/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: yangbuyi_video
 * @ClassName: CmsApplication
 * @create: 2021-10-11 23:16
 * @author: Yang Shuai
 * @since： JDK1.8
 * @CmsApplication: $
 **/


@SpringBootApplication
// 指定要扫描的Mapper类的包的路径
@MapperScan("top.yangbuyi.**.mapper")
// 扫描包路径
@ComponentScan(basePackages = {"top.yangbuyi"})
// 开启服务发现/注册
@EnableDiscoveryClient
public class CmsApplication {
    public static void main (String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}

