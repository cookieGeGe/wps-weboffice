package io.github.cookiegege.wpsOffice.registry;

import io.github.cookiegege.wpsOffice.spi.WpsCallbackService;
import lombok.Data;

/**
 * @author JoSuper
 * @date 2026/1/8 11:33
 */
@Data
public class HandlerDefinition {

    private final String bizCode;
    private final WpsCallbackService handler;

    public HandlerDefinition(String bizCode, WpsCallbackService handler) {
        this.bizCode = bizCode;
        this.handler = handler;
    }

}
