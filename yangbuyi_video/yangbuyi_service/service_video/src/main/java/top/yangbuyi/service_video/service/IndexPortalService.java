/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.service;

import top.yangbuyi.api_video.entity.Author;
import top.yangbuyi.api_video.entity.Category;
import top.yangbuyi.api_video.entity.Content;

import java.util.List;

/**
 * @program: yangbuyi_video
 * @ClassName: IndexPortalService
 * @create: 2021-10-12 00:07
 * @author: Yang Shuai
 * @since： JDK1.8
 * @IndexPortalService: $
 **/
public interface IndexPortalService {

    /**
     * 作品数据
     * @return
     */
    List<Content> getContentIndexList ();


    /**
     * 作者数据
     * @return
     */
    List<Author> getAuthorIndexList ();


    /**
     * 分类数据
     * @return
     */
    List<Category> getCategoryIndexList ();
}
