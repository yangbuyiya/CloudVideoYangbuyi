/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_cms.service;

import top.yangbuyi.service_cms.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author yangbuyi
 * @since 2021-10-11
 */
public interface BannerService extends IService<Banner> {
    /**
     * 缓存查询所有焦点图
     * @return
     */
    List<Banner> selectAllBanner ();

    /**
     * 编辑焦点图
     */
    boolean updateBanner(Banner banner);

    /**
     * 新增焦点图
     */
    boolean addBanner(Banner crmBanner);

    /**
     * 删除焦点图
     */
    void deleteBanner(String id);
}
