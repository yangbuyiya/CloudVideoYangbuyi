/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_vod.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: yangbuyi_video
 * @ClassName: VodService
 * @create: 2021-09-23 02:44
 * @author: Yang Shuai
 * @since： JDK1.8
 * @VodService: $
 **/

public interface VodService {


    String uploadVideo (MultipartFile file);

    void deleteVideo (String videoId);
}
