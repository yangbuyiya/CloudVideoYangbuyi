/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.yangbuyi.api_video.entity.Author;
import top.yangbuyi.api_video.entity.Category;
import top.yangbuyi.api_video.entity.Content;
import top.yangbuyi.service_video.service.AuthorService;
import top.yangbuyi.service_video.service.CategoryService;
import top.yangbuyi.service_video.service.ContentService;
import top.yangbuyi.service_video.service.IndexPortalService;

import java.util.List;

/**
 * @program: yangbuyi_video
 * @ClassName: IndexPortalServiceImpl
 * @create: 2021-10-12 00:09
 * @author: Yang Shuai
 * @since： JDK1.8
 * @IndexPortalServiceImpl: $
 **/
@Service
public class IndexPortalServiceImpl implements IndexPortalService {
    @Autowired
    private ContentService contentService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CategoryService categoryService;

    @Override
    @Cacheable(value = "index", key = "'getContentIndexList'")
    public List<Content> getContentIndexList () {
        QueryWrapper<Content> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<Content> contentList = contentService.list(wrapper);
        return contentList;
    }

    @Override
    @Cacheable(value = "index", key = "'getAuthorIndexList'")
    public List<Author> getAuthorIndexList () {
        //查询前4条作者
        QueryWrapper<Author> wrapperAuthor = new QueryWrapper<>();
        wrapperAuthor.orderByDesc("id");
        wrapperAuthor.last("limit 10");
        List<Author> authorList = authorService.list(wrapperAuthor);
        return authorList;
    }

    @Override
    @Cacheable(value = "index", key = "'getCategoryIndexList'")
    public List<Category> getCategoryIndexList () {
        QueryWrapper<Category> wrapperCategory = new QueryWrapper<>();
        wrapperCategory.orderByDesc("id");
        wrapperCategory.eq("parent_id", "0");
        wrapperCategory.last("limit 8");
        List<Category> categoryList = categoryService.list(wrapperCategory);
        return categoryList;
    }
}
