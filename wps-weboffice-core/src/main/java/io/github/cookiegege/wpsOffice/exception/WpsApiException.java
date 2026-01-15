package io.github.cookiegege.wpsOffice.exception;

import lombok.Getter;

/**
 * @author JoSuper
 * @date 2026/1/13 10:24
 */

@Getter
public class WpsApiException extends RuntimeException {
    private int code;
    private String requestId;

    public WpsApiException(int code, String msg, String requestId) {
        super(msg);
        this.code = code;
        this.requestId = requestId;
    }

    public WpsApiException(String msg) {
        super(msg);
    }

}
