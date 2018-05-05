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
import com.boot.cloudadmin.common.exception.GlobalException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 七牛云存储
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-05-06 03:41
 */
public class QiniuCloudStorageService extends CloudStorageService {
    private UploadManager uploadManager;
    private String token;

    public QiniuCloudStorageService(DeployUtil deployUtil){
        this.deployUtil = deployUtil;

        //初始化
        init();
    }

    private void init(){
        uploadManager = new UploadManager(new Configuration(Zone.autoZone()));
        token = Auth.create(deployUtil.getKey(), deployUtil.getSecret()).
                uploadToken(deployUtil.getBucket());
    }

    /**
     * 本地文件上传
     * @param localFilePath 本地文件路径
     * @param key 文件名
     * @return 返回文件的http地址 如 key = images/uuid.jpg 则返回 http://movie.lbtang.club/images/uuid.jpg
     */
    @Override
    public String upload(String localFilePath, String key) {
        try {
            Response res = uploadManager.put(localFilePath, key, token);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deployUtil.getOssurl() + "/" + key;
    }

    /**
     * 文件上传
     * @param data    文件字节数组
     * @param key    文件路径，包含文件名
     * @return        返回http地址
     */
    @Override
    public String upload(byte[] data, String key) {
        try {
            Response res = uploadManager.put(data, key, token);
            if (!res.isOK()) {
                throw new RuntimeException("上传七牛出错：" + res.toString());
            }
        } catch (Exception e) {
            throw new GlobalException("上传文件失败，请核对七牛配置信息", e);
        }

        return deployUtil.getOssurl() + "/" + key;
    }

    @Override
    public String upload(InputStream inputStream, String key) {
        try {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, key);
        } catch (IOException e) {
            throw new GlobalException("上传文件失败", e);
        }
    }
}
