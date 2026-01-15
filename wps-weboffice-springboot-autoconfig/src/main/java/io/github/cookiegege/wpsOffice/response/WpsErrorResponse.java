package io.github.cookiegege.wpsOffice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoSuper
 * @date 2026/1/13 15:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WpsErrorResponse {

    private int code;
    private String message;
    private String details;
    private String hint;


}
