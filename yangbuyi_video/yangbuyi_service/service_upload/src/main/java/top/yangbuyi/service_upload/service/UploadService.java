/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @program: yangbuyi_video
 * @ClassName: UploadService
 * @create: 2021-09-08 02:01
 * @author: Yang Shuai
 * @since： JDK1.8
 * @UploadService: $
 **/
public interface UploadService {
    /**
     * 上传文件
     *
     * @param file 文件
     * @return {@link String}
     */
    String uploadFile (MultipartFile file);
}
