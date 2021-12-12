/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_cms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.service_cms.entity.Banner;
import top.yangbuyi.service_cms.service.BannerService;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author yangbuyi
 * @since 2021-10-11
 */
@RestController
@RequestMapping("/service_cms/banner")
@RequiredArgsConstructor
public class BannerController {
    private final BannerService bannerService;

    /**
     * 查询所有banner
     * @return
     */
    @GetMapping("/getAllBanner")
    public ResponseResult getAllBanner() {
        return ResponseResult.ok().data("list",bannerService.selectAllBanner());
    }


    /**
     * 分页查询banner
     *
     * @param page  页面
     * @param limit 限制
     * @return {@link ResponseResult}
     */
    @RequestMapping("pageBanner/{page}/{limit}")
    public ResponseResult pageBanner(@PathVariable("page") long page, @PathVariable("limit") long limit,@RequestBody Banner banner) {
        Page<Banner> pageBanner = new Page<>(page,limit);
        bannerService.page(pageBanner,new LambdaQueryWrapper<Banner>().like(Banner::getTitle, banner.getTitle()));
        return ResponseResult.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }


    /**
     * 添加banner
     *
     * @param crmBanner crm的横幅
     * @return {@link ResponseResult}
     */
    @PostMapping("addBanner")
    public ResponseResult addBanner(@RequestBody Banner crmBanner) {
        boolean b = bannerService.addBanner(crmBanner);
        bannerService.selectAllBanner();
        return ResponseResult.toOk(b);
    }

    /**
     * 获取Banner
     *
     * @param id id
     * @return {@link ResponseResult}
     */
    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public ResponseResult get(@PathVariable String id) {
        Banner banner = bannerService.getById(id);
        return ResponseResult.ok().data("item", banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public ResponseResult updateById(@RequestBody Banner banner) {
        boolean b = bannerService.updateBanner(banner);
        bannerService.selectAllBanner();
        return ResponseResult.toOk(b);
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public ResponseResult remove(@PathVariable String id) {
        bannerService.deleteBanner(id);
        bannerService.selectAllBanner();
        return ResponseResult.ok();
    }
}

