/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.yangbuyi.api_video.entity.ContentVideo;
import top.yangbuyi.api_video.entity.vo.ContentVideoInfoVO;

/**
 * 内容视频服务
 * <p>
 * 作品视频 服务类
 * </p>
 *
 * @author yangbuyi
 * @date 2021/09/22
 * @since 2021-09-19
 */
public interface ContentVideoService extends IService<ContentVideo> {

    /**
     * 被章id数
     *
     * @param id id
     * @return boolean
     */
    boolean getCountByChapterId (String id);

    /**
     * 添加内容的视频
     *
     * @param contentVideoInfoVO 视频内容信息签证官
     * @return boolean
     */
    boolean addContentVideo (ContentVideoInfoVO contentVideoInfoVO);

    /**
     * 更新内容的视频
     *
     * @param contentVideoInfoVO 视频内容信息签证官
     * @return boolean
     */
    boolean updateContentVideo (ContentVideoInfoVO contentVideoInfoVO);

    /**
     * 删除内容视频丝毫id
     *
     * @param id id
     * @return boolean
     */
    boolean deleteContentVideoWhitById (String id);

    /**
     * 删除内容些微的视频内容id
     *
     * @param id id
     * @return boolean
     */
    boolean deleteContentVideoWhitByContentId (String id);
}
