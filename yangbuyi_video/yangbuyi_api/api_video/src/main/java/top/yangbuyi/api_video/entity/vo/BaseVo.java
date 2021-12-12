
/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.api_video.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @program: yangbuyi_viedo
 * @ClassName: BaseVo
 * @create: 2021-09-07 17:25
 * @author: Yang Shuai
 * @since： JDK1.8
 * @BaseVo: $
 **/
@Data
public class BaseVo implements Serializable {

    @ApiModelProperty(name = "当前页数")
    private Integer page;
    @ApiModelProperty(name = "每页记录数")
    private Integer limit;
    @ApiModelProperty(name = "其他参数")
    private Map<String, Object> params;


}
