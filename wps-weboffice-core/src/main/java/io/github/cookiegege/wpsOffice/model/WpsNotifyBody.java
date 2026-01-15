package io.github.cookiegege.wpsOffice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author JoSuper
 * @date 2026/1/8 16:04
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WpsNotifyBody {

    /**
     * 命令类型
     */
    private String cmd;

    /**
     * 回调内容（不同 cmd 内容不同）
     */
    private WpsNotifyBodyInfo body;


}
