package io.github.cookiegege.wpsOffice.config;

import io.github.cookiegege.wpsOffice.controller.WpsCallbackController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JoSuper
 * @date 2026/1/14 17:07
 */
@Configuration
public class WpsWebAutoConfiguration {

    @Bean
    public WpsCallbackController getWpsCallbackController() {
        return new WpsCallbackController();
    }

}
