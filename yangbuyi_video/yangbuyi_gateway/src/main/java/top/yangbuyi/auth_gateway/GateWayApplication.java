/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.auth_gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: yangbuyi_video
 * @ClassName: GateWayApplication
 * @create: 2021-09-25 01:56
 * @author: Yang Shuai
 * @since： JDK1.8
 * @GateWayApplication: 网关入口$
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class GateWayApplication {

    public static void main (String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }
}
