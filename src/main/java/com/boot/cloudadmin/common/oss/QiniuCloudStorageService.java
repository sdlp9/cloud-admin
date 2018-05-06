/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.boot.cloudadmin.common.oss;

import com.boot.cloudadmin.common.config.DeployUtil;
import com.boot.cloudadmin.common.enumobj.OssTypeEnum;
import com.boot.cloudadmin.common.exception.GlobalException;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FetchRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 七牛云存储
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-05-06 03:41
 */
public class QiniuCloudStorageService extends CloudStorageService {

    private static int LIMIT_SIZE = 1000;

    private UploadManager uploadManager;
    private Auth auth;
    private Configuration configuration;
    private BucketManager bucketManager;

    public QiniuCloudStorageService(DeployUtil config){
        this.deployUtil = config;
        //初始化
        init();
    }

    private void init(){
        configuration = new Configuration(Zone.autoZone());
        uploadManager = new UploadManager(configuration);
        auth = Auth.create(deployUtil.getKey(), deployUtil.getSecret());
        bucketManager = new BucketManager(auth,configuration);
    }

    @Override
    public String[] listBucket() {
        try {
            return bucketManager.buckets();
        }catch (QiniuException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<FileInfo> listFileOfBucket(String bucketName, String prefix, int limit) {
        BucketManager.FileListIterator it = bucketManager.createFileListIterator(bucketName, prefix, limit, null);
        List<FileInfo> list = new ArrayList<FileInfo>();
        while (it.hasNext()) {
            FileInfo[] items = it.next();
            if (null != items && items.length > 0) {
                list.addAll(Arrays.asList(items));
            }
        }
        return list;
    }

    @Override
    public String uploadFile(InputStream inputStream, String bucketName, String key, String mimeType) {
        try {
            String token = auth.uploadToken(bucketName);
            byte[] byteData = IOUtils.toByteArray(inputStream);
            Response response = uploadManager.put(byteData, key, token, null, mimeType, false);
            inputStream.close();
            return response.bodyString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String uploadFile(InputStream inputStream, String bucketName, String key) {
        try {
            String token = auth.uploadToken(bucketName);
            byte[] byteData = IOUtils.toByteArray(inputStream);
            Response response = uploadManager.put(byteData, key, token, null, null, false);
            inputStream.close();
            return response.bodyString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String uploadFile(String filePath, String fileName, String bucketName, String key) {
        try {
            String token = auth.uploadToken(bucketName);
            InputStream is = new FileInputStream(new File(filePath + fileName));
            byte[] byteData = IOUtils.toByteArray(is);
            Response response = uploadManager.put(byteData, (key == null || "".equals(key))? fileName : key, token);
            is.close();
            return response.bodyString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String uploadFile(String filePath, String fileName, String bucketName) {
        try {
            String token = auth.uploadToken(bucketName);
            InputStream is = new FileInputStream(new File(filePath + fileName));
            byte[] byteData = IOUtils.toByteArray(is);
            Response response = uploadManager.put(byteData, fileName, token);
            is.close();
            return response.bodyString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String fetchToBucket(String url, String bucketName, String key) {
        try {
            FetchRet putret = bucketManager.fetch(url, bucketName, key);
            return putret.key;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String fetchToBucket(String url, String bucketName) {
        try {
            FetchRet putret = bucketManager.fetch(url, bucketName);
            return putret.key;
        } catch (Exception e){
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public void copyFile(String bucket, String key, String targetBucket, String targetKey) {
        try {
            bucketManager.copy(bucket, key, targetBucket, targetKey);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void moveFile(String bucket, String key, String targetBucket, String targetKey) {
        try {
            bucketManager.move(bucket, key, targetBucket, targetKey);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void renameFile(String bucket, String key, String targetKey) {
        try {
            bucketManager.rename(bucket, key, targetKey);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFile(String bucket, String key) {
        try {
            bucketManager.delete(bucket, key);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public FileInfo[] findFiles(String bucketName, String prefix, int limit) {
        try {
            FileListing listing = bucketManager.listFiles(bucketName, prefix, null, limit, null);
            if(listing == null || listing.items == null || listing.items.length <= 0) {
                return null;
            }
            return listing.items;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FileInfo[] findFiles(String bucketName, String prefix) {
        try {
            FileListing listing = bucketManager.listFiles(bucketName, prefix, null, LIMIT_SIZE, null);
            if(listing == null || listing.items == null || listing.items.length <= 0) {
                return null;
            }
            return listing.items;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FileInfo[] findFiles(String bucketName) {
        try {
            FileListing listing = bucketManager.listFiles(bucketName, null, null, LIMIT_SIZE, null);
            if(listing == null || listing.items == null || listing.items.length <= 0) {
                return null;
            }
            return listing.items;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FileInfo findOneFile(String bucketName, String key, int limit) {
        try {
            FileListing listing = bucketManager.listFiles(bucketName, key, null, limit, null);
            if(listing == null || listing.items == null || listing.items.length <= 0) {
                return null;
            }
            return (listing.items)[0];
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FileInfo findOneFile(String bucketName, String key) {
        try {
            FileListing listing = bucketManager.listFiles(bucketName, key, null, LIMIT_SIZE, null);
            if(listing == null || listing.items == null || listing.items.length <= 0) {
                return null;
            }
            return (listing.items)[0];
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getFileAccessUrl(String key,int type) {
        if(type == OssTypeEnum.FILESURL.getValue()){
            return deployUtil.getFilesurl() + "/" + key;
        }
        if(type == OssTypeEnum.IMAGESURL.getValue()){
            return deployUtil.getImageurl() + "/" + key;
        }
        if(type == OssTypeEnum.VIEDOURL.getValue()){
            return deployUtil.getVideourl() + "/" + key;
        }
        return null;
    }
}
