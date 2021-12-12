/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.service_base.exception.BusinessException;
import top.yangbuyi.service_vod.service.VodService;
import top.yangbuyi.service_vod.utils.AliyunVodSDKUtils;
import top.yangbuyi.service_vod.utils.VodConstant;

/**
 * @program: yangbuyi_video
 * @ClassName: VodController
 * @create: 2021-09-23 02:42
 * @author: Yang Shuai
 * @since： JDK1.8
 * @VodController: $
 **/


@RestController
@Slf4j
@RequestMapping("/service_vod/vod")
@RequiredArgsConstructor
public class VodController {
    private final VodService vodService;

    @PostMapping("upload")
    public ResponseResult uploadVideo (@RequestParam("file") MultipartFile file) throws Exception {
        String videoId = vodService.uploadVideo(file);
        return ResponseResult.ok().message("视频上传成功").data("videoId", videoId);
    }

    @PostMapping("/delete-vod/{videoId}")
    public ResponseResult removeVideo (@PathVariable String videoId) {
        vodService.deleteVideo(videoId);
        return ResponseResult.ok().message("视频删除成功");
    }

    @GetMapping("/test-vod/{id}")
    public ResponseResult testVod (@PathVariable String id) {
        return ResponseResult.ok().data("请求成功:" + id);
    }


    @ApiOperation(value = "根据视频id获取视频凭证")
    @GetMapping("getPlayAuth/{id}")
    public ResponseResult getPlayAuth (@PathVariable String id) {
        try {
            String playAuth =  AliyunVodSDKUtils.init(id);
            return ResponseResult.ok().data("playAuth", playAuth);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("获取凭证失败");
        }
    }

}
