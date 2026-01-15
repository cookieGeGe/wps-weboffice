package io.github.cookiegege.wpsOffice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author JoSuper
 * @date 2026/1/8 15:43
 */
@Data
public class WpsFileHistory {

    private String id;
    private String name;
    private Integer version;
    private Integer size;

    @JsonProperty("download_url")
    private String downloadUrl;

    /**
     * 秒级时间戳
     */
    @JsonProperty("create_time")
    private Long createTime;

    @JsonProperty("modify_time")
    private Long modifyTime;


    private String creator;
    private String modifier;

    public static WpsFileHistory newInstance() {
        WpsFileHistory fileHistory = new WpsFileHistory();
        return fileHistory;
    }

}
