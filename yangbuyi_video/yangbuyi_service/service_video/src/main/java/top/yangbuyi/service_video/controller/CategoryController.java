/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.service_video.service.CategoryService;

/**
 * @program: yangbuyi_video
 * @ClassName: CategoryController
 * @create: 2021-09-09 01:17
 * @author: Yang Shuai
 * @since： JDK1.8
 * @CategoryController: 分类$
 **/

@Api(tags = "分类管理")
@RestController
@Slf4j
@RequestMapping("/service_video/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取所有分类信息")
    @GetMapping("/getAllCategory")
    public ResponseResult getAllCategory(){
        return ResponseResult.ok().data(categoryService.getTreeCategory());
    }

    @ApiOperation(value = "导入新增分类信息")
    @PostMapping("/addCategory")
    public ResponseResult addCategory(MultipartFile file){
        //调用业务上传excel
        categoryService.saveCategory(file);
        return ResponseResult.ok();
    }

}
