/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.yangbuyi.api_pay.RemotePayOrderClient;
import top.yangbuyi.api_video.RemoteContentClient;
import top.yangbuyi.api_video.entity.vo.ChapterVO;
import top.yangbuyi.api_video.entity.vo.ContentVo;
import top.yangbuyi.api_video.entity.vo.ContentWebVO;
import top.yangbuyi.common_utils.utils.JwtUtils;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.service_video.service.ChapterService;
import top.yangbuyi.service_video.service.ContentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 作品表 前端控制器
 * </p>
 *
 * @author yangbuyi
 * @since 2021-09-19
 */
@Api(tags = "作品管理")
@RestController
@RequestMapping("/service_video/content")
@RequiredArgsConstructor
public class ContentController implements RemoteContentClient {

    private final ContentService contentService;
    private final ChapterService chapterService;
    private final RemotePayOrderClient remotePayOrderClient;

    @ApiOperation(value = "作品内容新增")
    @PostMapping("/addContentInfo")
    public ResponseResult addContentInfo (@RequestBody ContentVo categoryVo) {
        return ResponseResult.ok().data("contentId", contentService.addContentInfo(categoryVo));
    }

    @ApiOperation(value = "根据ID获取作品详细信息")
    @GetMapping("/getContentWhitInfo/{id}")
    public ResponseResult getContentWhitInfo (@PathVariable String id) {
        return ResponseResult.ok().data(contentService.getContentWhitInfo(id));
    }

    @ApiOperation(value = "作品内容修改")
    @PostMapping("/updateContentInfo")
    public ResponseResult updateContentInfo (@RequestBody ContentVo categoryVo) {
        return ResponseResult.toOk(contentService.updateContentInfo(categoryVo));
    }

    @ApiOperation(value = "作品预览")
    @PostMapping("/getContentPreview/{id}")
    public ResponseResult getContentPreview (@PathVariable String id) {
        return this.contentService.getContentPreview(id);
    }

    @ApiOperation(value = "作品发布")
    @PostMapping("/sendContent/{id}")
    public ResponseResult sendContent (@PathVariable String id) {
        return this.contentService.sendContent(id);
    }

    @ApiOperation(value = "作品列表分页")
    @GetMapping("/getContentListPage")
    public ResponseResult getContentListPage (ContentVo categoryVo) {
        return this.contentService.getContentListPage(categoryVo);
    }


    @ApiOperation(value = "作品列表删除")
    @PostMapping("/deleteContentById/{id}")
    public ResponseResult deleteContentById (@PathVariable String id) {
        return this.contentService.deleteContentById(id);
    }


    @ApiOperation(value = "根据作品id获取作品详情信息")
    @GetMapping("/getContentDetailByContentId/{contentId}")
    public ResponseResult getContentDetail (@PathVariable String contentId, HttpServletRequest request) {
        //查询作品信息和作者信息
        ContentWebVO contentWebVo = contentService.selectContentDetailById(contentId);
        //查询当前作品的章节信息
        List<ChapterVO> chapterVoList = chapterService.getChapterContentVideo(contentId);

        // 判断当前作品是否已经支付
        String uid = JwtUtils.getUserIdByJwtToken(request);
        boolean isBuy = false;
        if (!StringUtils.isEmpty(uid)) {
            //查询是否已经付费过
            isBuy = remotePayOrderClient.getBuyContent(uid, contentId);
        }
        return ResponseResult.ok()
                       .data("content", contentWebVo)
                       .data("chapterVoList", chapterVoList)
                       .data("isBuyContent", isBuy);

    }


    @ApiOperation(value = "根据作品id查询作品信息")
    @GetMapping("/getContentInfoOrder/{id}")
    public ContentWebVO getContentInfoOrder (@PathVariable("id") String id) {
        // 查询作品信息和作者信息
        return contentService.selectContentDetailById(id);
    }

}

