package io.github.cookiegege.wpsOffice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author JoSuper
 * @date 2026/1/14 13:25
 */
@Data
public class WpsHistoryItem {

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


    private WpsUser creator;
    private WpsUser modifier;

    public static WpsHistoryItem newInstance() {
        WpsHistoryItem historyItem = new WpsHistoryItem();
        historyItem.setCreator(new WpsUser());
        historyItem.setModifier(new WpsUser());
        return historyItem;
    }

}
