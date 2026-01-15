package io.github.cookiegege.wpsOffice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author JoSuper
 * @date 2026/1/8 13:42
 */
@Data
public class WpsUser {

    private String id;

    private String name;

    @JsonProperty("avatar_url")
    private String avatarUrl = "";

    private String permission = "read";

}
