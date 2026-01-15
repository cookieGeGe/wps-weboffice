package io.github.cookiegege.wpsOffice.api;

import io.github.cookiegege.wpsOffice.context.WpsDocumentContext;

import java.util.Map;

/**
 * @author JoSuper
 * @date 2026/1/8 11:29
 */
public interface WpsDocumentService {

    String getEditUrl(String bizCode, String fileName, String fileId, String userId, Map<String, Object> ext);


    String getEditUrl(WpsDocumentContext wpsDocumentContext);


    String getPreviewUrl(String bizCode, String fileName, String fileId, String userId, Map<String, Object> ext);

    String getPreviewUrl(WpsDocumentContext wpsDocumentContext);

    String getPreviewUrlPreferEdit(WpsDocumentContext wpsDocumentContext);


}
