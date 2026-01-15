package io.github.cookiegege.wpsOffice.dispatcher.impl;

import cn.hutool.core.util.ObjectUtil;
import io.github.cookiegege.wpsOffice.dispatcher.WpsCallbackDispatcher;
import io.github.cookiegege.wpsOffice.exception.WpsWebOfficeException;
import io.github.cookiegege.wpsOffice.registry.CallbackHandlerRegistry;
import io.github.cookiegege.wpsOffice.registry.HandlerDefinition;
import io.github.cookiegege.wpsOffice.spi.WpsCallbackService;

/**
 * @author JoSuper
 * @date 2026/1/8 11:32
 */
public class DefaultWpsCallbackDispatcher implements WpsCallbackDispatcher {

    private final CallbackHandlerRegistry registry;

    public DefaultWpsCallbackDispatcher(CallbackHandlerRegistry registry) {
        this.registry = registry;
    }


    @Override
    public WpsCallbackService dispatch(String bizCode) {
        HandlerDefinition def = registry.getHandler(bizCode);
        if (ObjectUtil.isEmpty(def)) {
            throw new WpsWebOfficeException("No service found for bizCode: " + bizCode);
        }
        return def.getHandler();
    }
}
