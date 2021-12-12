/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_pay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: yangbuyi_video
 * @ClassName: PayApplication
 * @create: 2021-10-20 02:39
 * @author: Yang Shuai
 * @since： JDK1.8
 * @PayApplication: $
 **/


@SpringBootApplication
// 指定要扫描的Mapper类的包的路径
@MapperScan("top.yangbuyi.**.mapper")
// 扫描包路径
@ComponentScan(basePackages = {"top.yangbuyi"})
// 开启服务发现/注册
@EnableDiscoveryClient
// 开启远程调用
@EnableFeignClients(basePackages = {"top.yangbuyi"})
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }
}
