/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yangbuyi.api_video.entity.Category;
import top.yangbuyi.service_video.entity.excel.CategoryData;
import top.yangbuyi.service_video.service.CategoryService;

/**
 * @program: yangbuyi_video
 * @ClassName: CategoryDataListener
 * @create: 2021-09-09 01:13
 * @author: Yang Shuai
 * @since： JDK1.8
 * @CategoryDataListener: 监听器$分类
 **/

@Component
@Slf4j
public class CategoryDataListener extends AnalysisEventListener<CategoryData> {


    @Autowired
    private CategoryService categoryService;

    @Override
    public void invoke (CategoryData categoryData, AnalysisContext analysisContext) {
        log.info("当前行:" + categoryData.toString());
        //写入到数据库当中
        if (categoryData != null) {
            //如果一级分类不存在,就保存到数据库当中
            Category oneCategory = this.isExistOneCategory(categoryData);
            if (oneCategory == null) {
                oneCategory = new Category();
                oneCategory.setTitle(categoryData.getOneCategoryData());
                oneCategory.setParentId("0");
                categoryService.save(oneCategory);
            }
            //保存2级分类  先判断2级分类是否已经存在
            Category twoCategory = this.isExistTwoCategory(categoryData, oneCategory.getId());
            if (twoCategory == null) {
                twoCategory = new Category();
                twoCategory.setTitle(categoryData.getTwoCategoryData());
                twoCategory.setParentId(oneCategory.getId());
                categoryService.save(twoCategory);
            }
        }
    }

    /**
     * 判断1级分类是否已经存在
     *
     * @param categoryData 类别数据
     * @return {@link Category}
     */
    private Category isExistOneCategory (CategoryData categoryData) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", categoryData.getOneCategoryData());
        queryWrapper.eq("parent_id", "0");
        Category category = categoryService.getOne(queryWrapper);
        return category;
    }

    /**
     * 判断2级分类是否已经存在
     *
     * @param categoryData 类别数据
     * @param pid          pid
     * @return {@link Category}
     */
    private Category isExistTwoCategory (CategoryData categoryData, String pid) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", categoryData.getTwoCategoryData());
        queryWrapper.eq("parent_id", pid);
        Category category = categoryService.getOne(queryWrapper);
        return category;
    }


    @Override
    public void doAfterAllAnalysed (AnalysisContext analysisContext) {
        log.info("--------------------------------------------------");
        log.info("----------------------读取完毕----------------------");
        log.info("--------------------------------------------------");
    }
}
