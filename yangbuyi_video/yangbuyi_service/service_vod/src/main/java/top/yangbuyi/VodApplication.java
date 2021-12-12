/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: yangbuyi_video
 * @ClassName: VodApplication
 * @create: 2021-09-23 01:03
 * @author: Yang Shuai
 * @since： JDK1.8
 * @VodApplication: 视频点播$
 **/

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
// 开启服务发现/注册
@EnableDiscoveryClient
public class VodApplication {
    public static void main (String[] args) {
        SpringApplication.run(VodApplication.class, args);
    }
}
