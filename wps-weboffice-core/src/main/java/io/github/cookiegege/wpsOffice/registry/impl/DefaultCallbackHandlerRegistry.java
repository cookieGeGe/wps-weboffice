package io.github.cookiegege.wpsOffice.registry.impl;

import io.github.cookiegege.wpsOffice.registry.CallbackHandlerRegistry;
import io.github.cookiegege.wpsOffice.registry.HandlerDefinition;
import io.github.cookiegege.wpsOffice.spi.WpsCallbackService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JoSuper
 * @date 2026/1/13 14:15
 */
public class DefaultCallbackHandlerRegistry implements CallbackHandlerRegistry {

    private final Map<String, HandlerDefinition> handlerMap = new ConcurrentHashMap<>();

    @Override
    public void registerService(String bizCode, WpsCallbackService handler) {
        if (handlerMap.containsKey(bizCode)) {
            throw new IllegalStateException(
                    "Duplicate WpsCallbackHandler for bizCode: " + bizCode
            );
        }
        handlerMap.put(bizCode, new HandlerDefinition(bizCode, handler));
    }

    @Override
    public HandlerDefinition getHandler(String bizCode) {
        HandlerDefinition handlerDefinition = handlerMap.getOrDefault(bizCode, null);
        if (handlerDefinition == null) {
            throw new IllegalStateException(
                    "No WpsCallbackHandler found for bizCode: " + bizCode
            );
        }
        return handlerDefinition;
    }
}
