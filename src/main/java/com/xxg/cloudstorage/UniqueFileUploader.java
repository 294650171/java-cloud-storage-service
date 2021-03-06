package com.xxg.cloudstorage;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

/**
 * 通过UUID生成唯一不重复的文件名, 上传文件并返回对应的HTTP地址
 */
public class UniqueFileUploader {

    private CloudStorageService cloudStorageService;

    public void setCloudStorageService(CloudStorageService cloudStorageService) {
        this.cloudStorageService = cloudStorageService;
    }

    public String getFormattedBaseUrl() {
        String httpBase = cloudStorageService.getBaseUrl();
        if(!httpBase.endsWith("/")) {
            httpBase += "/";
        }
        return httpBase;
    }

    /**
     * 上传字节数组
     * @param data 字节数组
     * @param format 文件扩展名
     * @return 云存储HTTP地址
     * @throws Exception
     */
    public String upload(byte[] data, String format) throws Exception {
        String uuid = UUID.randomUUID().toString();
        String target = uuid + "." + format;
        String httpBase = getFormattedBaseUrl();
        cloudStorageService.upload(data, target);
        return httpBase + target;
    }

    /**
     * 上传字节流
     * @param inputStream 字节流
     * @param format 文件扩展名
     * @return 云存储HTTP地址
     * @throws Exception
     */
    public String upload(InputStream inputStream, String format) throws Exception {
        String uuid = UUID.randomUUID().toString();
        String target = uuid + "." + format;
        String httpBase = getFormattedBaseUrl();
        cloudStorageService.upload(inputStream, target);
        return httpBase + target;
    }

    /**
     * 上传本地文件
     * @param file 待上传的本地文件
     * @param format 文件扩展名
     * @return 云存储HTTP地址
     * @throws Exception
     */
    public String upload(File file, String format) throws Exception {
        String uuid = UUID.randomUUID().toString();
        String target = uuid + "." + format;
        String httpBase = getFormattedBaseUrl();
        cloudStorageService.upload(file, target);
        return httpBase + target;
    }
}
