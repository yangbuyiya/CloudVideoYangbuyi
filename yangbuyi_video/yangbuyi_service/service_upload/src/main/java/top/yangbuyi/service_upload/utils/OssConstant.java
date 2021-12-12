/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_upload.utils;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: yangbuyi_video
 * @ClassName: OSSConstant
 * @create: 2021-09-08 01:56
 * @author: Yang Shuai
 * @since： JDK1.8
 * @OSSConstant: oss文件上传$读取配置的属性信息
 **/

@Component
public class OssConstant implements InitializingBean {
    /**
     * 端点
     */
    @Value("${oss.endpoint}")
    private String endpoint;

    /**
     * 访问密钥id
     */
    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    /**
     * 访问密钥的秘密
     */
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    /**
     * bucket名称
     */
    @Value("${oss.bucketName}")
    private String bucketName;

    /**
     * 路径
     */
    @Value("${oss.path}")
    private String pathName;

    /**
     * 端点
     */
    public static String ENDPOINT;
    /**
     * 评估关键id
     */
    public static String ASSESS_KEY_ID;
    /**
     * 评估关键秘密
     */
    public static String ASSESS_KEY_SECRET;
    /**
     * bucket名称
     */
    public static String BUCKET_NAME;

    public static String PATH_NAME;

    /**
     * 在属性文件加载完毕后属性也设置完毕之后, 会自动调用
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet () throws Exception {
        ENDPOINT = endpoint;
        ASSESS_KEY_ID = accessKeyId;
        ASSESS_KEY_SECRET = accessKeySecret;
        BUCKET_NAME = bucketName;
        PATH_NAME = pathName;
    }
}