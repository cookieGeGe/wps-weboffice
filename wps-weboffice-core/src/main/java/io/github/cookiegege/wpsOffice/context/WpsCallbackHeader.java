package io.github.cookiegege.wpsOffice.context;

import lombok.Data;

/**
 * @author JoSuper
 * @date 2026/1/14 10:42
 */
@Data
public class WpsCallbackHeader {

    private String fileId;

    private String saveType;

    private String token;

}
