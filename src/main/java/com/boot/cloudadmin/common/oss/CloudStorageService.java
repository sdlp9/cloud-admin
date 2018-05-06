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
import com.boot.cloudadmin.common.utils.date.DateUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.model.FetchRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 云存储(支持七牛、阿里云、腾讯云、又拍云)
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-25 14:58
 */
public abstract class CloudStorageService {

    DeployUtil deployUtil;

    /**
     * 返回七牛帐号的所有空间
     * @return
     */
    public abstract String[] listBucket();

    /**
     * 获取指定空间下的文件列表
     * @param bucketName  空间名称
     * @param prefix      文件名前缀
     * @param limit       每次迭代的长度限制，最大1000，推荐值 100[即一个批次从七牛拉多少条]
     * @return List<FileInfo>
     * @throws
     */
    public abstract List<FileInfo> listFileOfBucket(String bucketName, String prefix, int limit);

    /**
     * 七牛图片上传
     * @param inputStream    待上传文件输入流
     * @param bucketName     空间名称
     * @param key            空间内文件的key
     * @param mimeType       文件的MIME类型，可选参数，不传入会自动判断
     * @return String
     */
    public abstract String uploadFile(InputStream inputStream, String bucketName, String key, String mimeType);

    /**
     * 七牛图片上传
     * @param inputStream    待上传文件输入流
     * @param bucketName     空间名称
     * @param key            空间内文件的key
     * @return String
     */
    public abstract String uploadFile(InputStream inputStream,String bucketName,String key);

    /**
     * 七牛图片上传
     * @param filePath     待上传文件的硬盘路径
     * @param fileName     待上传文件的文件名
     * @param bucketName   空间名称
     * @param key          空间内文件的key
     * @return String
     * @throws
     */
    public abstract String uploadFile(String filePath,String fileName,String bucketName,String key);

    /**
     * 七牛图片上传[若没有指定文件的key,则默认将fileName参数作为文件的key]
     * @param filePath     待上传文件的硬盘路径
     * @param fileName     待上传文件的文件名
     * @param bucketName   空间名称
     * @param @return
     * @param @throws IOException
     * @return String
     * @throws
     */
    public abstract String uploadFile(String filePath,String fileName,String bucketName);

    /**
     * 提取网络资源并上传到七牛空间里
     * @param url           网络上一个资源文件的URL
     * @param bucketName    空间名称
     * @param key           空间内文件的key[唯一的]
     * @return String
     */
    public abstract String fetchToBucket(String url,String bucketName,String key);

    /**
     * 提取网络资源并上传到七牛空间里,不指定key，则默认使用url作为文件的key
     * @param url
     * @param bucketName
     * @return String
     */
    public abstract String fetchToBucket(String url,String bucketName);

    /**
     * 七牛空间内文件复制
     * @param bucket         源空间名称
     * @param key            源空间里文件的key(唯一的)
     * @param targetBucket   目标空间
     * @param targetKey      目标空间里文件的key(唯一的)
     */
    public abstract void copyFile(String bucket, String key, String targetBucket, String targetKey);

    /**
     * 七牛空间内文件剪切
     * @param bucket         源空间名称
     * @param key            源空间里文件的key(唯一的)
     * @param targetBucket   目标空间
     * @param targetKey      目标空间里文件的key(唯一的)
     */
    public abstract void moveFile(String bucket, String key, String targetBucket, String targetKey);

    /**
     * 七牛空间内文件重命名
     * @param bucket
     * @param key
     * @param targetKey
     */
    public abstract void renameFile(String bucket, String key, String targetKey);

    /**
     * @Description: 七牛空间内文件删除
     * @param bucket    空间名称
     * @param key       空间内文件的key[唯一的]
     */
    public abstract void deleteFile(String bucket, String key);

    /**
     * 返回指定空间下的所有文件信息
     * @param @param bucketName   空间名称
     * @param @param prefix       文件key的前缀
     * @param @param limit        批量提取的最大数目
     * @return FileInfo[]
     * @throws
     */
    public abstract FileInfo[] findFiles(String bucketName,String prefix,int limit);

    /**
     * 返回指定空间下的所有文件信息
     * @param bucketName   空间名称
     * @param prefix       文件key的前缀
     * @return FileInfo[]
     * @throws
     */
    public abstract FileInfo[] findFiles(String bucketName,String prefix);

    /**
     * 返回指定空间下的所有文件信息
     * @param bucketName
     * @return FileInfo[]
     */
    public abstract FileInfo[] findFiles(String bucketName);

    /**
     * 返回指定空间下的某个文件
     * @param bucketName
     * @param key
     * @param limit
     * @return FileInfo
     * @throws
     */
    public abstract FileInfo findOneFile(String bucketName,String key,int limit);

    /**
     * 返回指定空间下的某个文件(重载)
     * @param bucketName
     * @param key
     * @return FileInfo
     */
    public abstract FileInfo findOneFile(String bucketName,String key);

    /**
     * 返回七牛空间内指定文件的访问URL
     * @param key
     * @return String
     */
    public abstract String getFileAccessUrl(String key,int type);
}
