/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_base.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: yangbuyi_viedo
 * @ClassName: MybatisPlusAppConfig
 * @create: 2021-09-05 22:42
 * @author: Yang Shuai
 * @since： JDK1.8
 * @MybatisPlusAppConfig: mp基础配置$
 **/

@Configuration
public class MybatisPlusAppConfig {

    /**
     * mybatisPlus逻辑删除
     *
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector () {
        return new LogicSqlInjector();
    }


    /**
     * 配置分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor () {
        return new PaginationInterceptor();
    }
}
