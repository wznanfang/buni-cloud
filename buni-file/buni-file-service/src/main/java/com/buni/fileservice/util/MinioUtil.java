package com.buni.fileservice.util;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author zp.wei
 * @date 2024/4/03 20:23
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class MinioUtil {


    @Resource
    @Qualifier("minioClient")
    private MinioClient minioClient;


    /**
     * 创建bucket
     */
    public void createBucket(String bucketName) throws Exception {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 获取全部bucket
     *
     * @return
     */
    public List<Bucket> getAllBuckets() throws Exception {
        return minioClient.listBuckets();
    }

    /**
     * 根据bucketName获取桶
     *
     * @param bucketName bucket名称
     */
    public Optional<Bucket> getBucket(String bucketName) throws Exception {
        return minioClient.listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
    }

    /**
     * 根据bucketName删除桶
     *
     * @param bucketName bucket名称
     */
    public void removeBucket(String bucketName) throws Exception {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }

    /**
     * 上传文件
     *
     * @param bucketName 数据桶
     * @param file       文件
     * @return 预览链接
     */
    public String uploadFile(String bucketName, MultipartFile file) {
        // 判断文件是否为空
        if (null == file || 0 == file.getSize()) {
            return "文件不存在！";
        }
        // 文件名
        String url = "";
        try {
            // 判断存储桶是否存在,不存在则创建
            createBucket(bucketName);
            String filename = file.getOriginalFilename();
            // 新的文件名 = 文件md5.后缀名
            InputStream inputStream = file.getInputStream();
            // 使用DigestUtils计算MD5值并以16进制字符串形式返回
            String md5 = DigestUtils.md5DigestAsHex(inputStream);
            // 关闭输入流
            inputStream.close();
            String fileName = md5 + filename.substring(filename.lastIndexOf("."));
            // 判断文件是否存在
            if (!checkFileExist(bucketName, fileName)) {
                // 开始上传
                putObject(bucketName, fileName, file.getInputStream());
            }
            url = getObjectURL(bucketName, fileName, 3);
        } catch (Exception e) {
            log.error("文件上传失败", e.fillInStackTrace());
        }
        return url;
    }

    /**
     * 获取⽂件外链
     *
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @param expires    过期时间
     * @return url
     */
    public String getObjectURL(String bucketName, String objectName, Integer expires) {
        String url = "";
        try {
            url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .expiry(expires, TimeUnit.HOURS)
                    .method(Method.GET)
                    .build());
        } catch (Exception e) {
            log.error("文件链接获取失败", e.fillInStackTrace());
        }
        return url;
    }

    /**
     * 获取⽂件
     *
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @return ⼆进制流
     */
    public InputStream getObject(String bucketName, String objectName) throws Exception {
        return minioClient.getObject(GetObjectArgs
                .builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }

    /**
     * 上传⽂件
     *
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @param stream     ⽂件流
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void putObject(String bucketName, String objectName, InputStream stream) throws Exception {
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(stream, stream.available(), -1)
                .contentType(objectName.substring(objectName.lastIndexOf("."))).build());
    }

    /**
     * 上传⽂件
     *
     * @param bucketName  bucket名称
     * @param objectName  ⽂件名称
     * @param stream      ⽂件流
     * @param size        ⼤⼩
     * @param contextType 类型
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void putObject(String bucketName, String objectName, InputStream stream, long size, String contextType) throws Exception {
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(stream, size, -1)
                .contentType(contextType).build());
    }

    /**
     * 获取⽂件信息
     *
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    public StatObjectResponse getObjectInfo(String bucketName, String objectName) throws Exception {
        return minioClient.statObject(StatObjectArgs
                .builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }

    /**
     * 删除⽂件
     *
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @throws Exception https://docs.minio.io/cn/java-client-apireference.html#removeObject
     */
    public void removeObject(String bucketName, String objectName) throws Exception {
        minioClient.removeObject(RemoveObjectArgs
                .builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }

    /**
     * 判断文件是否存在
     *
     * @param bucketName 桶名称
     * @param objectName 文件名称, 如果要带文件夹请用 / 分割, 例如 /help/index.html
     * @return true存在, 反之
     */
    public Boolean checkFileExist(String bucketName, String objectName) {
        try {
            minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 判断文件夹是否存在
     *
     * @param bucketName 桶名称
     * @param folderName 文件夹名称
     * @return true存在, 反之
     */
    public Boolean checkFolderExist(String bucketName, String folderName) {
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder()
                    .bucket(bucketName)
                    .prefix(folderName)
                    .recursive(false)
                    .build());
            for (Result<Item> result : results) {
                Item item = result.get();
                if (item.isDir() && folderName.equals(item.objectName())) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * minio桶之间数据复制
     *
     * @param sourceBucketName 源数据桶
     * @param targetBucketName 目标数据桶
     * @param recursive        是否递归查找，如果是false,就模拟文件夹结构查找
     * @param prefix           对象名称的前缀
     */
    public void copyBuckets(String sourceBucketName, String targetBucketName, boolean recursive, String prefix) {
        try {
            createBucket(targetBucketName);
            copy(sourceBucketName, targetBucketName, recursive, prefix);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void copy(String sourceBucketName, String targetBucketName, boolean recursive, String prefix) throws Exception {
        Iterable<Result<Item>> myObjects = minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(sourceBucketName).recursive(recursive).prefix(prefix).build());
        for (Result<Item> result : myObjects) {
            Item item = result.get();
            if (item.isDir()) {
                copy(sourceBucketName, targetBucketName, recursive, item.objectName());
            }
            minioClient.copyObject(CopyObjectArgs.builder().bucket(targetBucketName).object(item.objectName()).source(CopySource.builder()
                    .bucket(sourceBucketName).object(item.objectName()).build()).build());
        }
    }


}