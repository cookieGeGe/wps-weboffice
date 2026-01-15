package io.github.cookiegege.wpsOffice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author JoSuper
 * @date 2026/1/8 13:40
 */
@Data
public class WpsWatermark {

    private int type = 0;
    private String value = "";

    @JsonProperty("fillstyle")
    private String fillStyle = "rgba( 192, 192, 192, 0.6 )";

    private String font = "bold 20px Serif";
    private double rotate = -0.7853982;
    private int horizontal = 150;
    private int vertical = 150;

}
