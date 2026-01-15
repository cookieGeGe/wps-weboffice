package io.github.cookiegege.wpsOffice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author JoSuper
 * @date 2026/1/8 16:05
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WpsNotifyBodyInfo {

    @JsonProperty("file_id")
    private String fileId;

    private Long filesize;

    private String filetype;

    /**
     * 用户 id（有 userid / user_id 两种）
     */
    private String userid;

    @JsonProperty("user_id")
    private String userId;

    private String permission;

    private Integer counts;

    @JsonProperty("maxcounts")
    private Integer maxcounts;

    private Integer active;

    private String result;

    private String detail;

    @JsonProperty("operated_time")
    private String operatedTime;

    @JsonProperty("comment_id")
    private String commentId;

}
