/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_upload.service.impl;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.yangbuyi.service_upload.service.UploadService;
import top.yangbuyi.service_upload.utils.OssConstant;

import java.io.InputStream;
import java.util.UUID;

/**
 * @program: yangbuyi_video
 * @ClassName: UploadServiceImpl
 * @create: 2021-09-08 02:02
 * @author: Yang Shuai
 * @since： JDK1.8
 * @UploadServiceImpl: $
 **/

@Component
@Slf4j
public class UploadServiceImpl implements UploadService {


    /**
     * 上传文件
     *
     * @param file 文件
     * @return {@link String}
     */
    @Override
    public String uploadFile (MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = OssConstant.ENDPOINT;
        // 云账号AccessKey有所有API访问权限
        String accessKeyId = OssConstant.ASSESS_KEY_ID;
        String accessKeySecret = OssConstant.ASSESS_KEY_SECRET;
        String bucketName = OssConstant.BUCKET_NAME;
        // 创建OSSClient实例。
        OSS ossClient = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            //处理文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String fileName = uuid + file.getOriginalFilename();

            /*把同一天上传的文件 放到同一个文件夹当中  2020/10/1/fileName*/
            String date = new DateTime().toString("yyyy/MM/dd");
            fileName = date + "/" + fileName;

            ossClient.putObject(bucketName, fileName, inputStream);
            /*
            https://oss-yby.yangbuyi.top/2021/09/23/1c1bc81285b743c9acfb786962375a38file.png
            * */
            log.info("文件上次完毕:{}", OssConstant.PATH_NAME + fileName);
            return OssConstant.PATH_NAME + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
        return null;
    }
}
