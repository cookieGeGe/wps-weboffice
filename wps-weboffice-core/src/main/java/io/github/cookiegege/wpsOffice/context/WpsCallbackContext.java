package io.github.cookiegege.wpsOffice.context;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JoSuper
 * @date 2026/1/8 16:20
 */
@Data
public class WpsCallbackContext {

    private WpsCallbackHeader header;

    private String bizCode;

    private String fileId;

    private String userId;

    private Boolean isEdit;

    private Map<String, String> thirdParams;

    @JsonAnySetter
    public void setThirdParams(String key, String value) {
        if (ObjectUtil.isEmpty(thirdParams)) {
            thirdParams = new HashMap<>();
        }
        thirdParams.put(key, value);
    }


    public String getThirdParamByKey(String key) {
        return getThirdParamByKey(key, null);
    }

    public String getThirdParamByKey(String key, String defaultValue) {
        if (ObjectUtil.isEmpty(thirdParams)) {
            return defaultValue;
        }
        return thirdParams.getOrDefault(key, defaultValue);
    }

    public String getStringThirdParam(String key) {
        return getStringThirdParam(key, null);
    }

    public String getStringThirdParam(String key, String defaultValue) {
        return Convert.toStr(getThirdParamByKey(key), defaultValue);
    }


    public Boolean getBoolThirdParam(String key) {
        return getBoolThirdParam(key, null);
    }

    public Boolean getBoolThirdParam(String key, Boolean defaultValue) {
        return Convert.toBool(getThirdParamByKey(key), defaultValue);
    }

    public Integer getIntThirdParam(String key) {
        return getIntThirdParam(key, null);
    }

    public Integer getIntThirdParam(String key, Integer defaultValue) {
        return Convert.toInt(getThirdParamByKey(key), defaultValue);
    }

}
