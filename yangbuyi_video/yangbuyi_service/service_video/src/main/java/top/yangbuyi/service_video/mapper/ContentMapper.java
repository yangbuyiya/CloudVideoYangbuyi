/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.yangbuyi.api_video.entity.Content;
import top.yangbuyi.api_video.entity.vo.ContentPreviewVo;
import top.yangbuyi.api_video.entity.vo.ContentWebVO;

/**
 * @program: yangbuyi_video
 * @ClassName: ContentMapper
 * @create: 2021-09-20 18:49
 * @author: Yang Shuai
 * @since： JDK1.8
 * @ContentMapper: ${desc}$
 **/
public interface ContentMapper extends BaseMapper<Content> {
    ContentPreviewVo getContentPreviewWhitById (@Param("id") String id);

    ContentWebVO getContentDetailById (String id);
}