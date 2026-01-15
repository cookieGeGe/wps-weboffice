package io.github.cookiegege.wpsOffice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author JoSuper
 * @date 2026/1/8 13:35
 */
@Getter
@Setter
public class WpsUserAcl {

    private int rename = 0;
    private int history = 0;
    private int copy = 1;
    private int export = 1;
    private int print = 1;
    private int comment = 0;

    @JsonProperty("copy_control")
    private int copyControl = 0;
}
