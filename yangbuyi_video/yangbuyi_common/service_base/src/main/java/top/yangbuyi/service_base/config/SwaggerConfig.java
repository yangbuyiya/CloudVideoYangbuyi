/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_base.config;

import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: yangbuyi_viedo
 * @ClassName: SwaggerConfig
 * @create: 2021-09-05 23:01
 * @author: Yang Shuai
 * @since： JDK1.8
 * @SwaggerConfig: api管理$
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket webApiConfig () {
        return new Docket(DocumentationType.SWAGGER_2)
                       .groupName("接口文档在线调试")
                       .apiInfo(webApiInfo())
                       .select()
                       //排除指定的路径 不生成api
                       .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                       .paths(Predicates.not(PathSelectors.regex("/error.*")))
                       .build();
    }

    private ApiInfo webApiInfo () {
        return new ApiInfoBuilder()
                       .title("视频中心API文档")
                       .description("课程中心微服务接口")
                       .version("1.0")
                       .contact(new Contact("杨不易呀", "https://www.yangbuyi.com", "1692700664@qq.com"))
                       .build();
    }

}
