/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.api_video.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import top.yangbuyi.api_video.entity.vo.BaseVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: yangbuyi_video
 * @ClassName: ChapterVO
 * @create: 2021-09-20 20:01
 * @author: Yang Shuai
 * @since： JDK1.8
 * @ChapterVO: 章节前端接收参数$
 **/


@EqualsAndHashCode(callSuper = true)
@Data
public class ChapterVO extends BaseVo {

    private String id;
    private String title;
    private Integer sort;
    /**
     * 章节下,小节信息
     */
    private List<ContentVideoVO> children = new ArrayList<>();

}
