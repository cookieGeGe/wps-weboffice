package io.github.cookiegege.wpsOffice.request;

import io.github.cookiegege.wpsOffice.enums.PreviewMode;

import java.util.Map;

/**
 * @author JoSuper
 * @date 2026/1/13 13:57
 */
public interface WpsClient {

    WpsLinkResponse getEditLink(
            String fileId, String type, Map<String, String> thirdParams, Integer tokenType
    );

    WpsLinkResponse getPreviewLink(
            String fileId, String type,
            Map<String, String> thirdParams,
            Integer tokenType,
            PreviewMode previewMode,
            WpsPreviewOptions options
    );

}
