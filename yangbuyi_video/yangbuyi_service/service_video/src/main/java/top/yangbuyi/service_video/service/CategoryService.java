/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import top.yangbuyi.api_video.entity.Category;
import top.yangbuyi.api_video.entity.category.LevelCategory;

import java.util.List;

/**
 * @program: yangbuyi_video
 * @ClassName: CategoryService
 * @create: 2021-09-09 01:09
 * @author: Yang Shuai
 * @since： JDK1.8
 * @CategoryService: ${desc}$
 **/
public interface CategoryService extends IService<Category> {

    void saveCategory (MultipartFile file);

    List<LevelCategory> getTreeCategory ();

}
