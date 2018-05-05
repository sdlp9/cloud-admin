package com.boot.cloudadmin.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "deploy")
@PropertySource(value = "classpath:config/deploy.properties")
public class DeployUtil {

    private Long expiretime;

    private String host;

    private String uploadHost;

    private String bucket;

    private String ossflag;

    private String viewurl;

    private String imgPath;

    private String key;

    private String secret;

    private String ossurl;

    private String imagebucket;

    private String filebucket;

    private String videobucket;
}
