package io.github.cookiegege.wpsOffice.autoconfigure;

import io.github.cookiegege.wpsOffice.support.WpsCallbackHeaderResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author JoSuper
 * @date 2026/1/14 10:48
 */
@Configuration
@ConditionalOnWebApplication
public class WpsWebMvcAutoConfiguration implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new WpsCallbackHeaderResolver());
    }
}
