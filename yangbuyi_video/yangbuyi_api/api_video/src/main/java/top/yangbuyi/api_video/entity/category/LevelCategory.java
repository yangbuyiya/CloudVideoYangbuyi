/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.api_video.entity.category;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 水平分类
 *
 * @author TeouBle
 * @date 2021/09/18
 */
@Data
public class LevelCategory {
    /**
     * id
     */
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 孩子们
     */
    private List<LevelCategory> children = new ArrayList<>();
}
