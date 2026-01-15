package io.github.cookiegege.wpsOffice.support;

import io.github.cookiegege.wpsOffice.annotation.WpsCallbackProvider;
import io.github.cookiegege.wpsOffice.registry.CallbackHandlerRegistry;
import io.github.cookiegege.wpsOffice.spi.WpsCallbackService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ClassUtils;

import java.util.Map;

/**
 * @author JoSuper
 * @date 2026/1/8 12:02
 */
public class WpsCallbackHandlerScanner implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private final CallbackHandlerRegistry registry;

    public WpsCallbackHandlerScanner(CallbackHandlerRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {

        // 1. 找到所有实现了 WpsCallbackHandler 的 Bean
        Map<String, WpsCallbackService> handlerBeans =
                applicationContext.getBeansOfType(WpsCallbackService.class);

        for (WpsCallbackService handler : handlerBeans.values()) {

            // 2. 取真实 Class（防止被 AOP 代理）
            Class<?> targetClass = ClassUtils.getUserClass(handler);

            WpsCallbackProvider provider =
                    targetClass.getAnnotation(WpsCallbackProvider.class);

            if (provider == null) {
                continue;
            }

            String bizCode = provider.bizCode();

            if (bizCode == null || bizCode.trim().isEmpty()) {
                throw new IllegalStateException(
                        "@WpsCallbackProvider.bizCode must not be empty: "
                                + targetClass.getName()
                );
            }

            registry.registerService(bizCode, handler);
        }
    }

}
