/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_authority;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import top.yangbuyi.service_security.annotation.EnableCustomConfig;
import top.yangbuyi.service_security.annotation.EnableYsFeignClients;

/**
 * @program: yangbuyi_video
 * @ClassName: AuthorityApplication
 * @create: 2021-09-27 01:13
 * @author: Yang Shuai
 * @since： JDK1.8
 * @AuthorityApplication: 鉴权$
 **/

@EnableCustomConfig
@EnableYsFeignClients
@SpringCloudApplication
public class AuthorityApplication {

    public static void main (String[] args) {
        SpringApplication.run(AuthorityApplication.class, args);
    }

}
