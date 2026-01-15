package io.github.cookiegege.wpsOffice.enums;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author JoSuper
 * @date 2026/1/12 11:12
 */
@Getter
public enum WpsFileType {

    /*文字*/

    WORD("w",
            // 编辑支持格式
            setOf("doc", "dot", "wps", "wpt", "docx", "dotx", "docm", "dotm"),
            // 预览支持格式
            setOf("doc", "dot", "wps", "wpt", "docx", "dotx", "docm", "dotm",
                    "rtf", "txt", "mht", "mhtml", "htm", "html", "uot3")
    ),

    /*表格*/

    SHEET("s",
            setOf("xls", "xlt", "et", "xlsx", "xltx", "xlsm", "xltm"),
            setOf("xls", "xlt", "et", "xlsx", "xltx", "xlsm", "xltm",
                    "csv", "ett")
    ),

    /*演示*/

    SLIDE("p",
            setOf("ppt", "pptx", "pptm", "ppsx", "ppsm",
                    "pps", "potx", "potm", "dpt", "dps"),
            setOf("ppt", "pptx", "pptm", "ppsx", "ppsm",
                    "pps", "potx", "potm", "dpt", "dps", "pot")
    ),

    /*版式*/

    FIXED("f",
            setOf("pdf"),
            setOf("pdf", "ofd")
    ),

    /*预览专用*/

    IMAGE("x",
            Collections.emptySet(),
            setOf("jpeg", "jpg", "png", "gif", "bmp",
                    "tif", "tiff", "svg", "psd")
    ),

    ARCHIVE("x",
            Collections.emptySet(),
            setOf("tar", "zip", "7z", "jar", "rar", "gz")
    ),

    MARKDOWN("x",
            Collections.emptySet(),
            setOf("md")
    ),

    EMAIL("x",
            Collections.emptySet(),
            setOf("eml")
    ),

    CODE("x",
            Collections.emptySet(),
            setOf("c", "cpp", "java", "js", "css", "lrc", "h", "asm",
                    "s", "asp", "bat", "bas", "prg", "cmd", "xml")
    ),

    OTHER("x",
            Collections.emptySet(),
            setOf("log", "ini", "inf", "cdr", "vsd", "vsdx")
    );


    private final String type;

    private final Set<String> editSuffix;

    private final Set<String> previewSuffix;

    WpsFileType(String type, Set<String> editSuffix, Set<String> previewSuffix) {
        this.type = type;
        this.editSuffix = editSuffix;
        this.previewSuffix = previewSuffix;
    }

    public boolean supportsEdit(String suffix) {
        return editSuffix.contains(normalize(suffix));
    }

    public boolean supportsPreview(String suffix) {
        return previewSuffix.contains(normalize(suffix));
    }

    public boolean supports(String suffix, WpsCapability capability) {
        return capability == WpsCapability.EDIT
                ? supportsEdit(suffix)
                : supportsPreview(suffix);
    }


    public static WpsFileType fromSuffix(String suffix, WpsCapability capability) {
        if (StrUtil.isBlank(suffix)) {
            return null;
        }
        String s = normalize(suffix);
        for (WpsFileType type : values()) {
            if (type.supports(s, capability)) {
                return type;
            }
        }
        return null;
    }

    public static boolean isSupported(String suffix, WpsCapability capability) {
        return fromSuffix(suffix, capability) != null;
    }

    public static boolean isSupported(String suffix) {
        return isSupported(suffix, WpsCapability.EDIT) || isSupported(suffix, WpsCapability.PREVIEW);
    }


    private static String normalize(String suffix) {
        return suffix == null ? "" : suffix.toLowerCase();
    }

    private static Set<String> setOf(String... values) {
        return new HashSet<>(Arrays.asList(values));
    }

}
