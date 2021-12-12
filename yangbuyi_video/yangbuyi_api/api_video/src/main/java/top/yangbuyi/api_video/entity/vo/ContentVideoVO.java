/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.api_video.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * @program: yangbuyi_video
 * @ClassName: ContentVideoVO
 * @create: 2021-09-20 20:01
 * @author: Yang Shuai
 * @since： JDK1.8
 * @ContentVideoVO: 章节下小节前端接收参数$
 **/


@Data
public class ContentVideoVO implements Serializable {
    /**
     * id
     */
    private String id;
    /**
     * 小节标题
     */
    private String title;
    /**
     * 小节是否免费
     */
    private Boolean isFree;

    /**
     * 视频源id
     */
    private String videoSourceId;

    /**
     * 持续时间
     */
    private Float duration;


}
