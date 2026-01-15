package io.github.cookiegege.wpsOffice.context;

import cn.hutool.core.util.ObjectUtil;
import io.github.cookiegege.wpsOffice.enums.PreviewMode;
import io.github.cookiegege.wpsOffice.exception.WpsApiException;
import io.github.cookiegege.wpsOffice.request.WpsPreviewOptions;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JoSuper
 * @date 2026/1/8 16:20
 */
@Data
public class WpsDocumentContext {

    private String bizCode;

    private String fileId;

    private String userId;

    private String fileName;

    private PreviewMode previewMode;

    private WpsPreviewOptions wpsPreview;

    private Integer wTokenType;


    private Map<String, Object> thirdParams;

    public Map<String, String> getAllThirdParams() {
        Map<String, String> map = new HashMap<>();
        map.put("bizCode", bizCode);
        map.put("fileId", fileId);
        map.put("fileName", fileName);
        map.put("userId", userId);
        thirdParams.forEach((k, v) -> map.put(k, v.toString()));
        return map;
    }

    public void checkParam() {
        String bizCode = this.getBizCode();
        String fileId = this.getFileId();
        String fileName = this.getFileName();
        if (!ObjectUtil.isAllNotEmpty(fileId, fileName, bizCode)) {
            throw new WpsApiException("请检查参数！");
        }
    }

}
