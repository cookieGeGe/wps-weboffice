package io.github.cookiegege.wpsOffice.api.impl;

import cn.hutool.core.util.ObjectUtil;
import io.github.cookiegege.wpsOffice.api.WpsDocumentService;
import io.github.cookiegege.wpsOffice.context.WpsDocumentContext;
import io.github.cookiegege.wpsOffice.enums.WpsCapability;
import io.github.cookiegege.wpsOffice.enums.WpsFileType;
import io.github.cookiegege.wpsOffice.exception.WpsApiException;
import io.github.cookiegege.wpsOffice.exception.WpsWebOfficeException;
import io.github.cookiegege.wpsOffice.request.WpsClient;
import io.github.cookiegege.wpsOffice.request.WpsLinkResponse;
import io.github.cookiegege.wpsOffice.utils.WpsUtil;

import java.util.Map;

/**
 * @author JoSuper
 * @date 2026/1/12 10:15
 */
public class DefaultWpsDocumentService implements WpsDocumentService {

    private final WpsClient wpsClient;

    public DefaultWpsDocumentService(WpsClient wpsClient) {
        this.wpsClient = wpsClient;
    }


    @Override
    public String getEditUrl(String bizCode, String fileName, String fileId, String userId, Map<String, Object> ext) {
        WpsDocumentContext wpsDocumentContext = new WpsDocumentContext();
        wpsDocumentContext.setBizCode(bizCode);
        wpsDocumentContext.setFileName(fileName);
        wpsDocumentContext.setFileId(fileId);
        wpsDocumentContext.setUserId(userId);
        wpsDocumentContext.setThirdParams(ext);
        return getEditUrl(wpsDocumentContext);
    }

    @Override
    public String getEditUrl(WpsDocumentContext wpsDocumentContext) {
        wpsDocumentContext.checkParam();
        String fileId = wpsDocumentContext.getFileId();
        String fileName = wpsDocumentContext.getFileName();

        String fileSuffix = WpsUtil.getFileSuffix(fileName);
        if (!WpsFileType.isSupported(fileSuffix, WpsCapability.EDIT)) {
            throw new WpsApiException("不支持的文件类型：" + fileSuffix);
        }
        WpsFileType wpsFileType = WpsFileType.fromSuffix(fileSuffix, WpsCapability.EDIT);
        assert wpsFileType != null;
        WpsLinkResponse editLink = wpsClient.getEditLink(
                fileId,
                wpsFileType.getType(),
                wpsDocumentContext.getAllThirdParams(),
                wpsDocumentContext.getWTokenType()
        );
        return editLink.getLink();
    }

    @Override
    public String getPreviewUrl(String bizCode, String fileName, String fileId, String userId, Map<String, Object> ext) {
        WpsDocumentContext wpsDocumentContext = new WpsDocumentContext();
        wpsDocumentContext.setBizCode(bizCode);
        wpsDocumentContext.setFileName(fileName);
        wpsDocumentContext.setFileId(fileId);
        wpsDocumentContext.setUserId(userId);
        wpsDocumentContext.setThirdParams(ext);
        return getPreviewUrl(wpsDocumentContext);
    }

    @Override
    public String getPreviewUrl(WpsDocumentContext wpsDocumentContext) {
        wpsDocumentContext.checkParam();
        String fileId = wpsDocumentContext.getFileId();
        String fileName = wpsDocumentContext.getFileName();

        String fileSuffix = WpsUtil.getFileSuffix(fileName);
        if (!WpsFileType.isSupported(fileSuffix, WpsCapability.PREVIEW)) {
            throw new WpsApiException("不支持的文件类型：" + fileSuffix);
        }
        WpsFileType wpsFileType = WpsFileType.fromSuffix(fileSuffix, WpsCapability.PREVIEW);
        assert wpsFileType != null;
        WpsLinkResponse editLink = wpsClient.getPreviewLink(
                fileId,
                wpsFileType.getType(),
                wpsDocumentContext.getAllThirdParams(),
                wpsDocumentContext.getWTokenType(),
                wpsDocumentContext.getPreviewMode(),
                wpsDocumentContext.getWpsPreview()
        );
        return editLink.getLink();
    }


    @Override
    public String getPreviewUrlPreferEdit(WpsDocumentContext wpsDocumentContext) {
        String fileName = wpsDocumentContext.getFileName();
        if (ObjectUtil.isEmpty(fileName)) {
            throw new WpsWebOfficeException("参数错误，缺少文件名！");
        }
        String fileSuffix = WpsUtil.getFileSuffix(fileName);
        if (!WpsFileType.isSupported(fileSuffix)) {
            throw new WpsWebOfficeException("不支持的文件类型：" + fileSuffix);
        }
        if (WpsFileType.isSupported(fileSuffix, WpsCapability.EDIT)) {
            return getEditUrl(wpsDocumentContext);
        }
        return getPreviewUrl(wpsDocumentContext);
    }
}
