/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.yangbuyi.api_video.entity.Chapter;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.service_video.service.ChapterService;

/**
 * <p>
 * 作品章节 前端控制器
 * </p>
 *
 * @author yangbuyi
 * @since 2021-09-19
 */
@RestController
@RequestMapping("/service_video/chapter")
@RequiredArgsConstructor
public class ChapterController {
    private final ChapterService chapterService;

    @ApiOperation(value = "获取章节列表")
    @GetMapping("getChapterContentVideo/{contentId}")
    public ResponseResult getChapterContentVideo (@PathVariable String contentId) {
        return ResponseResult.ok().data(chapterService.getChapterContentVideo(contentId));
    }

    @ApiOperation(value = "新增章节")
    @PostMapping
    public ResponseResult insertChapter (@RequestBody Chapter chapter) {
        return ResponseResult.toOk(chapterService.save(chapter));
    }

    @ApiOperation(value = "修改章节")
    @PutMapping
    public ResponseResult updateChapter (@RequestBody Chapter chapter) {
        return ResponseResult.toOk(chapterService.updateById(chapter));
    }

    @ApiOperation(value = "删除章节")
    @DeleteMapping("{id}")
    public ResponseResult deleteChapterById (@ApiParam(name = "id", value = "章节ID", required = true) @PathVariable String id) {
        return ResponseResult.toOk(chapterService.deleteChapterById(id));
    }

    @ApiOperation(value = "章节或小节排序最大值")
    @PostMapping("/getContentOrVideoSortMax/{type}")
    public ResponseResult getContentOrVideoSortMax (@PathVariable Integer type) {
        return ResponseResult.ok().data(this.chapterService.getContentOrVideoSortMax(type));
    }

}

