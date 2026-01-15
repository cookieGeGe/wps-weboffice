package io.github.cookiegege.wpsOffice.autoconfigure;

import cn.hutool.core.bean.BeanUtil;
import io.github.cookiegege.wpsOffice.api.WpsDocumentService;
import io.github.cookiegege.wpsOffice.api.impl.DefaultWpsDocumentService;
import io.github.cookiegege.wpsOffice.config.WpsCoreProperties;
import io.github.cookiegege.wpsOffice.dispatcher.WpsCallbackDispatcher;
import io.github.cookiegege.wpsOffice.dispatcher.impl.DefaultWpsCallbackDispatcher;
import io.github.cookiegege.wpsOffice.registry.CallbackHandlerRegistry;
import io.github.cookiegege.wpsOffice.registry.impl.DefaultCallbackHandlerRegistry;
import io.github.cookiegege.wpsOffice.request.WpsClient;
import io.github.cookiegege.wpsOffice.request.impl.DefaultWpsClient;
import io.github.cookiegege.wpsOffice.support.WpsCallbackHandlerScanner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JoSuper
 * @date 2026/1/8 12:01
 */
@Configuration
@EnableConfigurationProperties(WpsProperties.class)
public class WpsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public WpsCoreProperties getWpsCoreProperties(WpsProperties wpsProperties) {
        return BeanUtil.toBean(wpsProperties, WpsCoreProperties.class);
    }


    @Bean
    @ConditionalOnMissingBean
    public WpsClient getWpsClient(WpsCoreProperties wpsCoreProperties) {
        return new DefaultWpsClient(wpsCoreProperties);
    }


    @Bean
    @ConditionalOnMissingBean
    public WpsDocumentService getWpsDocumentService(WpsClient wpsClient) {
        return new DefaultWpsDocumentService(wpsClient);
    }


    @Bean
    @ConditionalOnMissingBean
    public CallbackHandlerRegistry getCallbackHandlerRegistry() {
        return new DefaultCallbackHandlerRegistry();
    }


    @Bean
    @ConditionalOnMissingBean
    public WpsCallbackDispatcher getWpsCallbackDispatcher(CallbackHandlerRegistry callbackHandlerRegistry) {
        return new DefaultWpsCallbackDispatcher(callbackHandlerRegistry);
    }

    @Bean
    @ConditionalOnMissingBean
    public WpsCallbackHandlerScanner getWpsCallbackHandlerScanner(CallbackHandlerRegistry callbackHandlerRegistry) {
        return new WpsCallbackHandlerScanner(callbackHandlerRegistry);
    }


}
