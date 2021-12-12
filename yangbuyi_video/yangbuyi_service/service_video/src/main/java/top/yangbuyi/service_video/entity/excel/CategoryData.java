/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.entity.excel;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @program: yangbuyi_video
 * @ClassName: CategoryData
 * @create: 2021-09-09 01:10
 * @author: Yang Shuai
 * @since： JDK1.8
 * @CategoryData: $
 **/
@Data
public class CategoryData {

    @ExcelProperty(index = 0)
    private String oneCategoryData;

    @ExcelProperty(index = 1)
    private String twoCategoryData;

}
