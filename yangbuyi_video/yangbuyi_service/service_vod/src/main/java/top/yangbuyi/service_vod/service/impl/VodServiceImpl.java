/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_vod.service.impl;


import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.yangbuyi.common_utils.utils.ResultCode;
import top.yangbuyi.service_base.exception.BusinessException;
import top.yangbuyi.service_vod.service.VodService;
import top.yangbuyi.service_vod.utils.AliyunVodSDKUtils;
import top.yangbuyi.service_vod.utils.VodConstant;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: yangbuyi_video
 * @ClassName: VodServiceImpl
 * @create: 2021-09-23 02:44
 * @author: Yang Shuai
 * @since： JDK1.8
 * @VodServiceImpl: $
 **/

@Service
@Slf4j
public class VodServiceImpl implements VodService {


    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            // 获取文件输入流
            InputStream inputStream = file.getInputStream();
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            //截取标题
            assert originalFilename != null;
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            // 获取上传请求
            UploadStreamRequest request = new UploadStreamRequest(
                    VodConstant.ACCESS_KEY_ID,
                    VodConstant.ACCESS_KEY_SECRET,
                    title, originalFilename, inputStream);
            // 开始上传
            UploadVideoImpl uploader = new UploadVideoImpl();
            // 上传完毕后,服务器给于响应
            UploadStreamResponse response = uploader.uploadStream(request);
            // 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" + response.getCode() + ", message：" + response.getMessage();
                log.error(errorMessage);
                if (StringUtils.isEmpty(videoId)) {
                    throw new BusinessException(ResultCode.ERROR, errorMessage);
                }
            }
            return videoId;
        } catch (IOException e) {
            throw new BusinessException(ResultCode.ERROR, "vod 服务上传失败");
        }
    }

    @Override
    public void deleteVideo(String videoId) {
        try {
            //获取客户端请求
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    VodConstant.ACCESS_KEY_ID,
                    VodConstant.ACCESS_KEY_SECRET);
            //创建删除请示
            DeleteVideoRequest request = new DeleteVideoRequest();
            //设置要删除的视频ID
            request.setVideoIds(videoId);
            //删除请求后给予的响应
            DeleteVideoResponse response = client.getAcsResponse(request);
            log.info("删除视频资源成功:{}", response);
        } catch (ClientException e) {
            throw new BusinessException(ResultCode.ERROR, "视频删除失败");
        }
    }
}
