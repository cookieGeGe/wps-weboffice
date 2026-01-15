package io.github.cookiegege.wpsOffice.exception;

import lombok.Getter;

/**
 * @author JoSuper
 * @date 2026/1/8 11:58
 */
@Getter
public class WpsWebOfficeException extends RuntimeException {

    private final Integer code;

    private final String message;

    public WpsWebOfficeException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public WpsWebOfficeException(String message) {
        this.code = 500;
        this.message = message;
    }

}
