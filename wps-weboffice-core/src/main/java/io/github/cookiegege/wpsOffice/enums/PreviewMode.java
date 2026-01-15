package io.github.cookiegege.wpsOffice.enums;

import lombok.Getter;

/**
 * @author JoSuper
 * @date 2026/1/13 10:25
 */
@Getter
public enum PreviewMode {

    HIGH_DEFINITION("high_definition"),
    ORDINARY("ordinary"),
    CACHE("cache"),
    OFFICIAL("official");

    private final String value;

    PreviewMode(String value) {
        this.value = value;
    }

}
