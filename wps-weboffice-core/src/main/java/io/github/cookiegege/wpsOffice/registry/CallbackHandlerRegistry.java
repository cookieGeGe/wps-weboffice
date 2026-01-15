package io.github.cookiegege.wpsOffice.registry;

import io.github.cookiegege.wpsOffice.spi.WpsCallbackService;

/**
 * @author JoSuper
 * @date 2026/1/8 11:33
 */
public interface CallbackHandlerRegistry {


    void registerService(String bizCode, WpsCallbackService handler) ;

    HandlerDefinition getHandler(String bizCode);


}
