package io.github.cookiegege.wpsOffice.dispatcher;

import io.github.cookiegege.wpsOffice.spi.WpsCallbackService;

/**
 * @author JoSuper
 * @date 2026/1/12 10:42
 */
public interface WpsCallbackDispatcher {


    WpsCallbackService dispatch(String bizCode);

}
