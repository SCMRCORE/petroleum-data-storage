package com.petroleumserver.utils;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.petroleumcommom.properties.MinioProperties;
import com.petroleumpojo.dto.WanGongDTO;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.checkerframework.checker.optional.qual.MaybePresent;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class MinioUtils {

    @Resource
    private MinioClient minioClient;
    @Resource
    private MinioProperties minioProperties;
    /**
     * 上传word文件
     * @param file
     */
    public WanGongDTO upload(MultipartFile file) throws IOException {
        String bucketName = minioProperties.getBucketName();
        try {
            // 检查桶是否存在（如果不存在可以选择创建）
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            // 文件上传
            String objectName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            try (InputStream inputStream = file.getInputStream()) {
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(bucketName)
                                .object(objectName)
                                .stream(inputStream, file.getSize(), -1) // -1表示未知的内容大小限制
                                .contentType(file.getContentType()) // 设置文件类型
                                .build()
                );
            }
            // 返回文件访问url
            String url =  minioProperties.getEndpoint() + "/" + minioProperties.getBucketName() + '/' +  objectName;
            String fileName = file.getOriginalFilename();
            String uploadTIme = DateTime.now().toString("yyyy年MM月dd日-HH:mm:ss");
            return WanGongDTO.builder()
                    .fileName(fileName)
                    .url(url)
                    .uploadTime(uploadTIme)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("文件上传失败: " + e.getMessage());
        }
    }
}
