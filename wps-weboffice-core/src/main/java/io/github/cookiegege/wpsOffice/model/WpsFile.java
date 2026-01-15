package io.github.cookiegege.wpsOffice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author JoSuper
 * @date 2026/1/8 13:43
 */
@Data
public class WpsFile {

    private String id;
    private String name;
    private int version = 1;
    private Integer size;
    private boolean readonly = false;

    private String creator;

    @JsonProperty("create_time")
    private Long createTime;

    private String modifier;

    @JsonProperty("modify_time")
    private Long modifyTime;

    @JsonProperty("download_url")
    private String downloadUrl;

    @JsonProperty("preview_pages")
    private int previewPages = 0;

    @JsonProperty("user_acl")
    private WpsUserAcl userAcl;


    private WpsWatermark watermark;
    private WpsAttr attrs;

    public static WpsFile newInstance() {
        WpsFile file = new WpsFile();
        file.setUserAcl(new WpsUserAcl());
        file.setWatermark(new WpsWatermark());
        file.setAttrs(new WpsAttr());
        return file;
    }


}
