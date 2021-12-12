/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_base.handler;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: yangbuyi_viedo
 * @ClassName: MetaObjectHandler
 * @create: 2021-09-07 16:30
 * @author: Yang Shuai
 * @since： JDK1.8
 * @MetaObjectHandler: 自动填充$
 **/

@Component
@Slf4j
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    /**
     * 新增时自动填充该字段数据
     *
     * @param metaObject
     */
    @Override
    public void insertFill (MetaObject metaObject) {
        log.info("字段数据填充配置");
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }

    /**
     * 更新时填充字段
     *
     * @param metaObject
     */
    @Override
    public void updateFill (MetaObject metaObject) {
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }
}

