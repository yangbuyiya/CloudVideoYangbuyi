/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import top.yangbuyi.service_cms.entity.Banner;
import top.yangbuyi.service_cms.mapper.BannerMapper;
import top.yangbuyi.service_cms.service.BannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author yangbuyi
 * @since 2021-10-11
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

    /**
     * 缓存查询所有焦点图
     *
     * @return
     */
    @Cacheable(value = "banner", key = "'selectIndexList'")
    @Override
    public List<Banner> selectAllBanner () {
        //根据id进行降序排列，显示排列之后前两条记录
        QueryWrapper<Banner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //last方法，拼接sql语句
        wrapper.last("limit 3");
        return baseMapper.selectList(null);
    }

    @Override
    @CacheEvict(value = "banner", allEntries = true)
    public boolean updateBanner(Banner banner){
        return this.updateById(banner);
    }

    @Override
    @CacheEvict(value = "banner", allEntries = true)
    public boolean addBanner(Banner crmBanner) {
        return this.save(crmBanner);
    }

    @Override
    @CacheEvict(value = "banner", allEntries = true)
    public void deleteBanner(String id) {
        this.removeById(id);
    }
}
