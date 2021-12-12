/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.api_video.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @program: yangbuyi_video
 * @ClassName: Content
 * @create: 2021-09-20 18:49
 * @author: Yang Shuai
 * @since： JDK1.8
 * @Content: ${desc}$
 **/

@ApiModel(value = "top-yangbuyi-service_video-entity-Content")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "`video_content`")
public class Content implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 作品ID
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "作品ID")
    private String id;

    /**
     * 作者ID
     */
    @TableField(value = "`author_id`")
    @ApiModelProperty(value = "作者ID")
    private String authorId;

    /**
     * 分类ID
     */
    @TableField(value = "`category_id`")
    @ApiModelProperty(value = "分类ID")
    private String categoryId;

    /**
     * 分类父级ID
     */
    @TableField(value = "`category_parent_id`")
    @ApiModelProperty(value = "分类父级ID")
    private String categoryParentId;

    /**
     * 作品标题
     */
    @TableField(value = "`title`")
    @ApiModelProperty(value = "作品标题")
    private String title;

    /**
     * 价格，设置为0则可免费观看
     */
    @TableField(value = "`price`")
    @ApiModelProperty(value = "价格，设置为0则可免费观看")
    private BigDecimal price;

    /**
     * 作品个数
     */
    @TableField(value = "`content_num`")
    @ApiModelProperty(value = "作品个数")
    private Integer contentNum;

    /**
     * 作品
     */
    @TableField(value = "`cover`")
    @ApiModelProperty(value = "作品")
    private String cover;

    /**
     * 销售数量
     */
    @TableField(value = "`buy_count`")
    @ApiModelProperty(value = "销售数量")
    private Long buyCount;

    /**
     * 浏览数量
     */
    @TableField(value = "`view_count`")
    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

    /**
     * 乐观锁
     */
    @TableField(value = "`version`")
    @ApiModelProperty(value = "乐观锁")
    private Long version;

    /**
     * 课程状态 Draft未发布  Normal已发布
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    public static final String COL_ID = "id";

    public static final String COL_AUTHOR_ID = "author_id";

    public static final String COL_CATEGORY_ID = "category_id";

    public static final String COL_CATEGORY_PARENT_ID = "category_parent_id";

    public static final String COL_TITLE = "title";

    public static final String COL_PRICE = "price";

    public static final String COL_CONTENT_NUM = "content_num";

    public static final String COL_COVER = "cover";

    public static final String COL_BUY_COUNT = "buy_count";

    public static final String COL_VIEW_COUNT = "view_count";

    public static final String COL_VERSION = "version";

    public static final String COL_STATUS = "status";

    public static final String COL_IS_DELETED = "is_deleted";

    public static final String COL_GMT_CREATE = "gmt_create";

    public static final String COL_GMT_MODIFIED = "gmt_modified";
}