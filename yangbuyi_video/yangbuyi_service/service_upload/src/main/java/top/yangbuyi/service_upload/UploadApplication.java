/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: yangbuyi_video
 * @ClassName: uploadApplication
 * @create: 2021-09-08 01:51
 * @author: Yang Shuai
 * @since： JDK1.8
 * @uploadApplication: 文件上传服务$
 **/

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class UploadApplication {

    public static void main (String[] args) {
        SpringApplication.run(UploadApplication.class, args);
    }

}
