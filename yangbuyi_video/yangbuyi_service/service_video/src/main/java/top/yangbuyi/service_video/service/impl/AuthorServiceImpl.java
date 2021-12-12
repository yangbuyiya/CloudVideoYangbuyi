/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.util.StringUtils;
import top.yangbuyi.api_video.entity.Author;
import top.yangbuyi.api_video.entity.vo.AuthorQuery;
import top.yangbuyi.service_video.mapper.AuthorMapper;
import top.yangbuyi.service_video.service.AuthorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 创作者 服务实现类
 * </p>
 *
 * @author yangbuyi
 * @since 2021-09-05
 */
@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements AuthorService {

    @Override
    public void pageQuery (Page<Author> pageInfo, AuthorQuery authorQuery) {
        QueryWrapper<Author> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_modified");
        queryWrapper.orderByAsc("sort");
        if (authorQuery == null) {
            //条件查询
            baseMapper.selectPage(pageInfo, queryWrapper);
            return;
        }
        /*有条件 判断条件是否为空*/
        if (!StringUtils.isEmpty(authorQuery.getName())) {
            /*模糊查询name*/
            queryWrapper.like("name", authorQuery.getName());
        }
        if (!StringUtils.isEmpty(authorQuery.getLevel())) {
            queryWrapper.eq("level", authorQuery.getLevel());
        }
        /*查询创建的时间大于等于开始时间*/
        if (!StringUtils.isEmpty(authorQuery.getBegin())) {
            queryWrapper.ge("gmt_create", authorQuery.getBegin());
        }
        /*查询创建的时间在小于等于结束时间*/
        if (!StringUtils.isEmpty(authorQuery.getEnd())) {
            queryWrapper.le("gmt_create", authorQuery.getEnd());
        }
        baseMapper.selectPage(pageInfo, queryWrapper);

    }
}
