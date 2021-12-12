/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.api_video.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: yangbuyi_viedo
 * @ClassName: AuthorQuery
 * @create: 2021-09-05 23:19
 * @author: Yang Shuai
 * @since： JDK1.8
 * @AuthorQuery: 前端接收参数$
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthorQuery extends BaseVo{

    private String name;
    private Integer level;
    private String begin;
    private String end;

}

