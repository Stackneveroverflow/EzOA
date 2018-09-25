package cn.sweetyhut.ezoa.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Demo class
 *
 * @author Macer
 * @version V1.0
 * @date 2018/09/25 21:54
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class MiniProgramConfig {
    private String appId;

    private String secretKey;

    private String wxCode2SessionApi;
}
