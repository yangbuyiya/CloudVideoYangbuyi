/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: yangbuyi_viedo
 * @ClassName: VideoApplication
 * @create: 2021-09-05 22:27
 * @author: Yang Shuai
 * @since： JDK1.8
 * @VideoApplication: 视频服务入口$
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
public class VideoApplication {

    public static void main (String[] args) {
        SpringApplication.run(VideoApplication.class, args);
    }

}
