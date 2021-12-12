/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yangbuyi.api_video.entity.ContentVideo;
import top.yangbuyi.api_video.entity.vo.ContentVideoInfoVO;
import top.yangbuyi.api_vod.VodClient;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.common_utils.utils.ResultCode;
import top.yangbuyi.service_base.exception.BusinessException;
import top.yangbuyi.service_video.mapper.ContentVideoMapper;
import top.yangbuyi.service_video.service.ContentVideoService;

/**
 * <p>
 * 作品小节视频 服务实现类
 * </p>
 *
 * @author yangbuyi
 * @since 2021-09-19
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ContentVideoServiceImpl extends ServiceImpl<ContentVideoMapper, ContentVideo> implements ContentVideoService {

    private final VodClient vodClient;

    @Override
    public boolean getCountByChapterId (String id) {
        final LambdaQueryWrapper<ContentVideo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ContentVideo::getChapterId, id);
        return baseMapper.selectCount(wrapper) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addContentVideo (ContentVideoInfoVO contentVideoInfoVO) {
        final ContentVideo contentVideo = new ContentVideo();
        BeanUtils.copyProperties(contentVideoInfoVO, contentVideo);
        return this.save(contentVideo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateContentVideo (ContentVideoInfoVO contentVideoInfoVO) {
        final ContentVideo contentVideo = new ContentVideo();
        BeanUtils.copyProperties(contentVideoInfoVO, contentVideo);
        return this.updateById(contentVideo);
    }

    @Override
    public boolean deleteContentVideoWhitById (String id) {
        // 删除小节下的视频
        final ContentVideo contentVideo = this.baseMapper.selectById(id);
        // 获取小节视频ID
        final String videoSourceId = contentVideo.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)) {
            // 删除视频
            final ResponseResult responseResult = vodClient.removeVideo(videoSourceId);
            if (!responseResult.getSuccess()) {
                throw new BusinessException(ResultCode.ERROR, "远程调用视频删除失败!");
            }
            log.info("小节视频删除成功:" + responseResult);
        } else {
            log.error("视频资源不存在删除失败!: {0}" + id);
        }
        return this.baseMapper.deleteById(id) > 0 ? true : false;
    }

    /**
     * 删除内容些微的视频内容id
     *
     * @param id id
     * @return boolean
     */
    @Override
    public boolean deleteContentVideoWhitByContentId (String id) {
        QueryWrapper<ContentVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("content_id", id);
        return baseMapper.delete(queryWrapper) > 0;
    }
}
