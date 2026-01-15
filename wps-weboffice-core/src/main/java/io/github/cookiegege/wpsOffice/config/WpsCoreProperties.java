package io.github.cookiegege.wpsOffice.config;


import lombok.Getter;
import lombok.Setter;

/**
 * @author JoSuper
 * @date 2026/1/8 11:49
 */
@Getter
@Setter
public class WpsCoreProperties {

    private String endpoint;

    private String accessKey;

    private String secretKey;

    private String clientId;

    private String keyPrefix = "_w_third_";

    private Boolean verify = true;


}
