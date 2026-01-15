package io.github.cookiegege.wpsOffice.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cookiegege.wpsOffice.context.WpsCallbackContext;
import io.github.cookiegege.wpsOffice.exception.WpsWebOfficeException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JoSuper
 * @date 2026/1/8 17:19
 */
public class WpsUtil {

    public static Map<String, String> removePrefix(Map<String, String> map, String keyPrefix) {
        int keyLength = keyPrefix.length();
        HashMap<String, String> result = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String newKey = key;
            if (StrUtil.startWith(key, keyPrefix)) {
                newKey = key.substring(keyLength);
            }
            result.put(newKey, value);
        }
        return result;
    }

    public static Map<String, String> addPrefix(Map<String, String> map, String keyPrefix) {
        HashMap<String, String> result = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String newKey = key;
            if (!StrUtil.startWith(key, keyPrefix)) {
                newKey = keyPrefix + key;
            }
            result.put(newKey, value);
        }
        return result;
    }

    public static String getFileSuffix(String fileName) {
        List<String> nameList = StrUtil.split(fileName, ".");
        if (ObjectUtil.isEmpty(nameList)) {
            throw new WpsWebOfficeException("");
        }
        return nameList.get(nameList.size() - 1);
    }


    public static WpsCallbackContext convertToCallbackContext(Map<String, String> map, String keyPrefix) {
        Map<String, String> callbackContextMap = removePrefix(map, keyPrefix);
        ObjectMapper mapper = new ObjectMapper();
        try {
            WpsCallbackContext wpsCallbackContext = mapper.readValue(
                    JSONUtil.toJsonStr(callbackContextMap), WpsCallbackContext.class
            );
            return wpsCallbackContext;
        } catch (Exception e) {
            throw new WpsWebOfficeException("参数转换失败：" + e.getMessage());
        }

    }


}
