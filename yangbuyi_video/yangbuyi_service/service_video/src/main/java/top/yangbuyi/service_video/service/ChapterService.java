/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yangbuyi.api_video.entity.Chapter;
import top.yangbuyi.api_video.entity.vo.ChapterVO;

import java.util.List;

/**
 * <p>
 * 作品章节 服务类
 * </p>
 *
 * @author yangbuyi
 * @since 2021-09-19
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVO> getChapterContentVideo (String contentId);

    boolean deleteChapterById (String id);

    Integer getContentOrVideoSortMax (Integer type);

    boolean deleteChapterByContentId (String id);
}
