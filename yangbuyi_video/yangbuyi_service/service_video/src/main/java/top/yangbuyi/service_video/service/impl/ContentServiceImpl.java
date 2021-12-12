/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yangbuyi.common_utils.constant.Constants;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.api_video.entity.Content;
import top.yangbuyi.api_video.entity.ContentDescription;
import top.yangbuyi.api_video.entity.vo.ContentVo;
import top.yangbuyi.api_video.entity.vo.ContentWebVO;
import top.yangbuyi.service_video.mapper.ContentMapper;
import top.yangbuyi.service_video.service.ChapterService;
import top.yangbuyi.service_video.service.ContentDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.yangbuyi.service_video.service.ContentService;
import top.yangbuyi.service_video.service.ContentVideoService;

import java.util.List;


/**
 * <p>
 * 作品表 服务实现类
 * </p>
 *
 * @author yangbuyi
 * @since 2021-09-19
 */
@Service
@RequiredArgsConstructor
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    /**
     * 内容描述服务
     */
    private final ContentDescriptionService contentDescriptionService;

    /**
     * 作品内容视频服务
     */
    private final ContentVideoService contentVideoService;

    /**
     * 作品章节服务
     */
    private final ChapterService chapterService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addContentInfo (ContentVo categoryVo) {
        final Content content = new Content();
        BeanUtils.copyProperties(categoryVo, content);
        baseMapper.insert(content);
        final ContentDescription contentDescription = new ContentDescription();
        contentDescription.setId(content.getId());
        contentDescription.setDescription(categoryVo.getDescription());
        contentDescriptionService.save(contentDescription);
        return content.getId();
    }

    @Override
    public ContentVo getContentWhitInfo (String id) {
        ContentVo contentVo = new ContentVo();
        Content byId = baseMapper.selectById(id);
        BeanUtils.copyProperties(byId, contentVo);
        ContentDescription description = contentDescriptionService.getById(id);
        if (description != null) {
            contentVo.setDescription(description.getDescription());
        }
        return contentVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateContentInfo (ContentVo contentVo) {
        final Content content = new Content();
        BeanUtils.copyProperties(contentVo, content);
        final ContentDescription byId = contentDescriptionService.getById(content.getId());
        byId.setDescription(contentVo.getDescription());
        contentDescriptionService.updateById(byId);
        return baseMapper.updateById(content) > 0;
    }

    @Override
    public ResponseResult getContentPreview (String id) {
        return ResponseResult.ok().data(this.baseMapper.getContentPreviewWhitById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult sendContent (String id) {
        final Content content = new Content();
        content.setId(id);
        content.setStatus(Constants.CONTENT_STATUS_NORMAL);
        return ResponseResult.toOk(this.updateById(content));
    }

    @Override
    public ResponseResult getContentListPage (ContentVo contentVo) {
        final Page<Content> contentPage = new Page<>(contentVo.getPage(), contentVo.getLimit());
        final LambdaQueryWrapper<Content> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNoneBlank(contentVo.getCategoryParentId()), Content::getCategoryParentId, contentVo.getCategoryParentId())
                .eq(StringUtils.isNoneBlank(contentVo.getCategoryId()), Content::getCategoryId, contentVo.getCategoryId())
                .eq(StringUtils.isNoneBlank(contentVo.getAuthorId()), Content::getAuthorId, contentVo.getAuthorId())
                .like(StringUtils.isNoneBlank(contentVo.getTitle()), Content::getTitle, contentVo.getTitle())
                .eq(Content::getIsDeleted, 0);

        if (contentVo.getType() != null) {
            if (contentVo.getType() == 1) {
                wrapper.orderByDesc(Content::getBuyCount);
            }
            if (contentVo.getType() == 2) {
                wrapper.orderByDesc(Content::getGmtCreate);
            }
            if (contentVo.getType() == 3) {
                wrapper.orderByDesc(Content::getPrice);
            }
        }

        this.baseMapper.selectPage(contentPage, wrapper);
        return ResponseResult.ok().dataPage(contentPage.getRecords(), contentPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult deleteContentById (String id) {
        // 1.删除关联的小节与视频
        this.contentVideoService.deleteContentVideoWhitByContentId(id);
        // 2.删除关联的章节
        this.chapterService.deleteChapterByContentId(id);
        // 3.删除作品
        final int i = this.baseMapper.deleteById(id);
        return ResponseResult.toOk(i > 0);
    }

    @Override
    public List<Content> selectByAuthorId (String authorId) {
        QueryWrapper<Content> queryWrapper = new QueryWrapper<Content>();
        queryWrapper.eq("author_id", authorId);
        // 按照最后更新时间倒序排列
        queryWrapper.orderByDesc("gmt_modified");
        List<Content> contents = baseMapper.selectList(queryWrapper);
        return contents;
    }

    /**
     * 获取作品详情与更新播放信息
     */
    @Override
    public ContentWebVO selectContentDetailById (String id) {
        this.updatePageViewCount(id);
        return baseMapper.getContentDetailById(id);
    }

    /**
     * 更新播放信息
     */
    @Override
    public ResponseResult updatePageViewCount (String id) {
        Content content = baseMapper.selectById(id);
        content.setViewCount(content.getViewCount() + 1);
        baseMapper.updateById(content);
        return ResponseResult.ok();
    }

}

