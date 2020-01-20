package com.ynet.poc.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;


@Slf4j
@Component
@Data
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOssUtil {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String fileHost;

    /**
     * 上传文件
     */
    public String upload(File file) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String _bucketName = this.getBucketName();
        // 判断文件
        if (file == null) {
            return null;
        }
        String fileUrl = "";
        OSSClient client = new OSSClient(this.getEndpoint(), this.getAccessKeyId(), this.getAccessKeySecret());
        try {
            // 判断容器是否存在,不存在就创建
            if (!client.doesBucketExist(_bucketName)) {
                client.createBucket(_bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(_bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                client.createBucket(createBucketRequest);
            }
            // 设置文件路径和名称
            String suffix = file.getName().substring(file.getName().lastIndexOf("."));
            fileUrl = this.getFileHost() + file.getName() + suffix;
            // 上传文件
            PutObjectResult result = client.putObject(new PutObjectRequest(_bucketName, fileUrl, file));
            // 设置权限(公开读)
            client.setBucketAcl(_bucketName, CannedAccessControlList.PublicRead);
            if (result != null) {
                log.info("------OSS文件上传成功------" + fileUrl);
            }

        } catch (OSSException oe) {
            log.error(oe.getMessage());
        } catch (ClientException ce) {
            log.error(ce.getErrorMessage());
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
        return fileUrl;
    }

    public String getFileNameSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
