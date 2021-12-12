/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yangbuyi.api_video.entity.Author;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yangbuyi.api_video.entity.vo.AuthorQuery;

/**
 * <p>
 * 创作者 服务类
 * </p>
 *
 * @author yangbuyi
 * @since 2021-09-05
 */
public interface AuthorService extends IService<Author> {

    /**
     * 页面查询
     *
     * @param pageInfo    页面信息
     * @param authorQuery 作者查询
     */
    void pageQuery (Page<Author> pageInfo, AuthorQuery authorQuery);
}
