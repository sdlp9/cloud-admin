package com.boot.cloudadmin.common.oss;

import com.boot.cloudadmin.common.config.DeployUtil;

public final class OSSFactory {

    public static CloudStorageService build(DeployUtil deployUtil){
        if(deployUtil.getOssflag().equals("1")){
            return new QiniuCloudStorageService(deployUtil);
        }
        /*else if(deployUtil.getOssflag().equals("2")){
            return new AliyunCloudStorageService(deployUtil);
        }else if(deployUtil.getOssflag().equals("3")){
            return new QcloudCloudStorageService(deployUtil);
        }*/

        return null;
    }
}
