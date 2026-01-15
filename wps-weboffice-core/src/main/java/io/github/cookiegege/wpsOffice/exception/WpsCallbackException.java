package io.github.cookiegege.wpsOffice.exception;

import lombok.Getter;

/**
 * @author JoSuper
 * @date 2026/1/13 16:00
 */
@Getter
public class WpsCallbackException extends RuntimeException {

    private final int code;
    private final String hint;

    public WpsCallbackException(int code, String message, String hint) {
        super(message);
        this.code = code;
        this.hint = hint;
    }

}
