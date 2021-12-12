/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import top.yangbuyi.api_video.entity.Chapter;
import top.yangbuyi.api_video.entity.ContentVideo;
import top.yangbuyi.api_video.entity.vo.ChapterVO;
import top.yangbuyi.api_video.entity.vo.ContentVideoVO;
import top.yangbuyi.common_utils.utils.ResultCode;
import top.yangbuyi.service_base.exception.BusinessException;
import top.yangbuyi.service_video.mapper.ChapterMapper;
import top.yangbuyi.service_video.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yangbuyi.service_video.service.ContentVideoService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 作品章节 服务实现类
 * </p>
 *
 * @author yangbuyi
 * @since 2021-09-19
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private ContentVideoService contentVideoService;

    @Override
    public List<ChapterVO> getChapterContentVideo (String contentId) {
        // 根据课程ID 获取章节
        final LambdaQueryWrapper<Chapter> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Chapter::getContentId, contentId).orderByAsc(Chapter::getSort);
        final List<Chapter> chapters = baseMapper.selectList(queryWrapper);
        // 根据课程ID 获取小节
        final LambdaQueryWrapper<ContentVideo> contentVideoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        contentVideoLambdaQueryWrapper.eq(ContentVideo::getContentId, contentId);
        final List<ContentVideo> contentVideoList = contentVideoService.list(contentVideoLambdaQueryWrapper);
        // 组装章节数据
        final ArrayList<ChapterVO> list = new ArrayList<ChapterVO>(16);
        // 组装数据
        for (Chapter chapter : chapters) {
            final ChapterVO chapterVO = new ChapterVO();
            BeanUtils.copyProperties(chapter, chapterVO);
            // 小节
            final ArrayList<ContentVideoVO> v1 = new ArrayList<ContentVideoVO>();
            for (ContentVideo video : contentVideoList) {
                if (video.getChapterId().equals(chapterVO.getId())) {
                    ContentVideoVO vo = new ContentVideoVO();
                    BeanUtils.copyProperties(video, vo);
                    v1.add(vo);
                }
            }
            // 将小节设置到章节当中
            chapterVO.setChildren(v1);
            list.add(chapterVO);
        }
        return list;
    }

    @Override
    public boolean deleteChapterById (String id) {
        //根据id查询是否存在小节，如果有则提示用户尚有子节点
        if (contentVideoService.getCountByChapterId(id)) {
            throw new BusinessException(ResultCode.ERROR, "该分章节下存在小节，请先删除小节");
        }
        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }

    @Override
    public Integer getContentOrVideoSortMax (Integer type) {
        return this.baseMapper.getContentOrVideoSortMax(type);
    }

    @Override
    public boolean deleteChapterByContentId (String id) {
        final LambdaQueryWrapper<Chapter> wrapper = new LambdaQueryWrapper<>();
        this.baseMapper.delete(wrapper.eq(Chapter::getContentId, id));
        return false;
    }

}
