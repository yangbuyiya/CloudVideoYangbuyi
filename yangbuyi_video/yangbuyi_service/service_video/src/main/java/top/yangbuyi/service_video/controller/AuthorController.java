/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.api_video.entity.Author;
import top.yangbuyi.api_video.entity.Content;
import top.yangbuyi.api_video.entity.vo.AuthorQuery;
import top.yangbuyi.service_video.service.AuthorService;
import top.yangbuyi.service_video.service.ContentService;

import java.util.List;

/**
 * <p>
 * 创作者 前端控制器
 * </p>
 *
 * @author yangbuyi
 * @since 2021-09-05
 */
@Api(tags = "作者组")
@RestController
@RequestMapping("/service_video/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final ContentService contentService;

    /**
     * 页面列表
     *
     * @param page  页面
     * @param limit 限制
     * @return {@link ResponseResult}
     */
    @ApiOperation(value = "作者分页列表数据")
    @PostMapping("/pageList/{page}/{limit}")
    public ResponseResult pageList(
            @ApiParam(name = "page", value = "当前页", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "authorQuery", value = "查询对象", required = false)
                    AuthorQuery authorQuery) {
        /*分页查询*/
        Page<Author> pageInfo = new Page<>(page, limit);
        /*调用业务做分页条件查询  查询的结果都封装到了pageInfo*/
        authorService.pageQuery(pageInfo, authorQuery);
        /*获取当前页的数据*/
        List<Author> records = pageInfo.getRecords();
        /*获取总记录*/
        long total = pageInfo.getTotal();
        return ResponseResult.ok().dataPage(records, total);
    }

    /**
     * 得到所有作者列表
     *
     * @return {@link ResponseResult}
     */
    @ApiOperation(value = "作者列表数据")
    @GetMapping("/getAllAuthorList")
    public ResponseResult getAllAuthorList() {
        return ResponseResult.ok().data(authorService.list(null));
    }


    /**
     * 根据作者ID查询详细信息
     *
     * @param id
     * @return {@link ResponseResult}
     */
    @ApiOperation("根据作者ID查询详细信息")
    @GetMapping("/getAuthorWithId/{id}")
    public ResponseResult getAuthorWithId(@ApiParam(name = "作者ID", required = true) @PathVariable String id) {
        //查询作者信息
        Author author = authorService.getById(id);
        //根据作者id查询这个作者的作品列表
        List<Content> contentList = contentService.selectByAuthorId(id);
        return ResponseResult.ok().data("author", author).data("contentList", contentList);
    }

    /**
     * 添加作者
     *
     * @param author 作者
     * @return {@link ResponseResult}
     */
    @ApiOperation(value = "添加作者")
    @PostMapping("/addAuthor")
    public ResponseResult addAuthor(
            @ApiParam(name = "author", value = "作者对象", required = true)
            @RequestBody Author author) {
        return ResponseResult.toOk(authorService.save(author));
    }

    /**
     * 更新创作者
     *
     * @param author
     * @return {@link ResponseResult}
     */
    @ApiOperation(value = "更新创作者")
    @PostMapping("/updateAuthor")
    public ResponseResult updateAuthor(
            @ApiParam(name = "author", value = "作者对象", required = true)
            @RequestBody Author author) {
        return ResponseResult.toOk(authorService.updateById(author));
    }

    /**
     * 根据作者ID删除信息
     *
     * @param id id
     * @return {@link ResponseResult}
     */
    @ApiOperation(value = "根据作者ID删除信息")
    @PostMapping("/deleteById/{id}")
    public ResponseResult deleteById(@ApiParam(name = "作者ID") @PathVariable Long id) {
        return ResponseResult.toOk(authorService.removeById(id));
    }

}

