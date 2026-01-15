package io.github.cookiegege.wpsOffice.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author JoSuper
 * @date 2026/1/13 14:27
 */
@Data
@ConfigurationProperties(prefix = "wps")
public class WpsProperties {

    private String endpoint = "";

    private String accessKey = "";

    private String secretKey = "";

    private String clientId = "";

    private String keyPrefix = "_w_third_";

    private Boolean verify = true;


    private WpsCallback callback = new WpsCallback();

    @Data
    public static class WpsCallback {

        private Boolean enabled = false;

        private String prefixUrl = "/";
    }


}
