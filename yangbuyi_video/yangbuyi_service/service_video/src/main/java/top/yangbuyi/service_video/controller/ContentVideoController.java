/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import top.yangbuyi.api_video.entity.ContentVideo;
import top.yangbuyi.api_video.entity.vo.ContentVideoInfoVO;
import top.yangbuyi.api_vod.VodClient;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.service_video.service.ContentVideoService;

/**
 * <p>
 * 作品视频 前端控制器
 * </p>
 *
 * @author yangbuyi
 * @since 2021-09-19
 */
@Api(tags = "小节管理")
@RestController
@RequestMapping("/service_video/contentVideo")
@RequiredArgsConstructor
public class ContentVideoController {

    private final ContentVideoService contentVideoService;
    private final VodClient vodClient;

    @ApiOperation(value = "新增小节")
    @PostMapping
    public ResponseResult addContentVideo (@RequestBody ContentVideoInfoVO contentVideoInfoVO) {
        return ResponseResult.toOk(contentVideoService.addContentVideo(contentVideoInfoVO));
    }

    @ApiOperation(value = "更新小节")
    @PutMapping
    public ResponseResult updateContentVideo (@RequestBody ContentVideoInfoVO contentVideoInfoVO) {
        return ResponseResult.toOk(contentVideoService.updateContentVideo(contentVideoInfoVO));
    }

    @ApiOperation(value = "根据ID获取小节")
    @GetMapping("{id}")
    public ResponseResult getContentVideoById (@PathVariable String id) {
        final ContentVideo byId = contentVideoService.getById(id);
        final ContentVideoInfoVO contentVideoInfoVO = new ContentVideoInfoVO();
        BeanUtils.copyProperties(byId, contentVideoInfoVO);
        return ResponseResult.ok().data(contentVideoInfoVO);
    }

    @ApiOperation(value = "根据ID删除小节")
    @DeleteMapping("{id}")
    public ResponseResult deleteContentVideoById (@PathVariable String id) {
        return ResponseResult.ok().data(contentVideoService.deleteContentVideoWhitById(id));
    }


    @GetMapping("/test-vod/{id}")
    public ResponseResult testVod (@PathVariable String id) {
        return vodClient.testVideo(id);
    }

}

