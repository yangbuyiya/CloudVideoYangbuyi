/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.yangbuyi.api_video.entity.Category;
import top.yangbuyi.api_video.entity.category.LevelCategory;
import top.yangbuyi.service_video.entity.excel.CategoryData;
import top.yangbuyi.service_video.listener.CategoryDataListener;
import top.yangbuyi.service_video.mapper.CategoryMapper;
import top.yangbuyi.service_video.service.CategoryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
*  @program:  yangbuyi_video
*  @ClassName:  CategoryServiceImpl
*  @create:  2021-09-09 01:09
*  @author:  Yang Shuai
*  @since： JDK1.8   
*  @CategoryServiceImpl: ${desc}$
**/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{

    @Autowired
    private CategoryDataListener categoryDataListener;

    @Override
    public void saveCategory (MultipartFile file) {
        //读取excel
        try {
            EasyExcel.read(file.getInputStream(), CategoryData.class,categoryDataListener).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LevelCategory> getTreeCategory() {
        //1.读取1级分类
        QueryWrapper<Category> oneQueryWrapper = new QueryWrapper<>();
        oneQueryWrapper.eq("parent_id",0);
        List<Category> oneCategories = baseMapper.selectList(oneQueryWrapper);
        //2.读取2级分类
        QueryWrapper<Category> twoQueryWrapper = new QueryWrapper<>();
        oneQueryWrapper.ne("parent_id",0);
        List<Category> twoCategories = baseMapper.selectList(twoQueryWrapper);
        //封装模型数据
        List<LevelCategory> resList = new ArrayList<>();
        for (Category oneCategory : oneCategories) {
            //把Category转成LevelCategory
            LevelCategory oneLevelCategory = new LevelCategory();
            //属性的复制
            //把前一个对象当中的属性值复制到后一个对象当中相同的属性中去
            //如果前一个对象在后一个对象当中没有找到相同的属性,就不复制
            BeanUtils.copyProperties(oneCategory,oneLevelCategory);

            //获取当前子分类有哪些
            //遍历所有的二级分类
            for (Category twoCategory : twoCategories) {
                //二级分类的parent_id=一级分类的id  就是当前一级分类 的子级
                if (twoCategory.getParentId().equals(oneCategory.getId())){
                    LevelCategory twoLevelCategory = new LevelCategory();
                    BeanUtils.copyProperties(twoCategory,twoLevelCategory);
                    oneLevelCategory.getChildren().add(twoLevelCategory);
                }
            }
            resList.add(oneLevelCategory);
        }
        Collections.reverse(resList);
        return resList;
    }
}
