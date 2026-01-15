package io.github.cookiegege.wpsOffice.request;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoSuper
 * @date 2026/1/13 10:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WpsLinkResponse {

    private WpsData data;
    private Integer code;
    private String msg;
    private Long request_time;
    private String request_id;
    private Long response_time;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WpsData {
        private String link;
    }

    public String getLink() {
        if (ObjectUtil.isEmpty(this.data)) {
            return "";
        }
        return this.data.getLink();
    }

}
