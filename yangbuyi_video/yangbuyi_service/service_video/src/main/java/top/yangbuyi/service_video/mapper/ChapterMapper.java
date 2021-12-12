/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import feign.Param;
import top.yangbuyi.api_video.entity.Chapter;

/**
 * <p>
 * 作品章节 Mapper 接口
 * </p>
 *
 * @author yangbuyi
 * @since 2021-09-19
 */
public interface ChapterMapper extends BaseMapper<Chapter> {

    Integer getContentOrVideoSortMax (@Param("type") Integer type);
}
