/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_video.controller.portal;

import com.aliyuncs.DefaultAcsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import top.yangbuyi.common_utils.utils.ResponseResult;
import top.yangbuyi.service_video.service.IndexPortalService;

/**
 * @program: yangbuyi_video
 * @ClassName: IndexPortalController
 * @create: 2021-10-12 00:05
 * @author: Yang Shuai
 * @since： JDK1.8
 * @IndexPortalController: 前端门户页面数据$获取视频播放
 **/


@RestController
@Slf4j
@RequestMapping("/service_video/indexPortal")
@RequiredArgsConstructor
public class IndexPortalController {

    /**
     * 索引门户服务
     */
    private final IndexPortalService indexPortalService;

    /**
     * 加载门户首页数据
     *
     * @return {@link ResponseResult}
     */
    @GetMapping("/getIndexData")
    public ResponseResult getIndexData() {
        return ResponseResult.ok().data("contentList",indexPortalService.getContentIndexList())
                       .data("authorList",indexPortalService.getAuthorIndexList())
                       .data("categoryList",indexPortalService.getCategoryIndexList());
    }

}
