package io.github.cookiegege.wpsOffice.request.impl;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import io.github.cookiegege.wpsOffice.config.WpsCoreProperties;
import io.github.cookiegege.wpsOffice.enums.PreviewMode;
import io.github.cookiegege.wpsOffice.exception.WpsApiException;
import io.github.cookiegege.wpsOffice.request.WpsClient;
import io.github.cookiegege.wpsOffice.request.WpsLinkResponse;
import io.github.cookiegege.wpsOffice.request.WpsPreviewOptions;
import io.github.cookiegege.wpsOffice.utils.WpsUtil;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author JoSuper
 * @date 2026/1/13 10:26
 */
public class DefaultWpsClient implements WpsClient {

    private final WpsCoreProperties properties;

    public DefaultWpsClient(WpsCoreProperties properties) {
        this.properties = properties;
    }

    @Override
    public WpsLinkResponse getEditLink(String fileId, String type, Map<String, String> thirdParams, Integer tokenType) {
        String path = "/open/api/edit/v1/files/" + fileId + "/link";
        Map<String, String> query = getQueryMap(type, null, null, tokenType, thirdParams);
        return sendGetRequest(path, query);
    }

    @Override
    public WpsLinkResponse getPreviewLink(String fileId, String type, Map<String, String> thirdParams, Integer tokenType,
                                          PreviewMode previewMode, WpsPreviewOptions options) {
        String path = "/open/api/preview/v1/files/" + fileId + "/link";
        Map<String, String> query = getQueryMap(type, previewMode, options, tokenType, thirdParams);
        return sendGetRequest(path, query);
    }


    public Map<String, String> getQueryMap(
            String type, PreviewMode previewMode, WpsPreviewOptions options,
            Integer tokenType, Map<String, String> thirdParams
    ) {
        Map<String, String> query = new HashMap<>();
        query.put("type", type);
        if (ObjectUtil.isNotEmpty(previewMode)) {
            query.put("preview_mode", previewMode.getValue());
        }
        if (ObjectUtil.isNotEmpty(options)) {
            query.put("wpsPreview", options.toBitString());
        }
        if (ObjectUtil.isNotEmpty(tokenType)) {
            query.put("_w_tokentype", tokenType.toString());
        }
        if (ObjectUtil.isEmpty(thirdParams)) {
            thirdParams = new HashMap<>();
        }
        String clientId = properties.getClientId();
        String clientIdKey = "client_id";
        if (!thirdParams.containsKey(clientIdKey) && ObjectUtil.isNotEmpty(clientId)) {
            thirdParams.put(clientIdKey, clientId);
        }
        Map<String, String> newThirdParams = WpsUtil.addPrefix(thirdParams, properties.getKeyPrefix());
        query.putAll(newThirdParams);
        return query;
    }


    public void setSslFactory(HttpRequest httpRequest) {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            httpRequest.setSSLSocketFactory(socketFactory);
        } catch (Exception e) {
            throw new WpsApiException(e.getMessage());
        }

    }

    private WpsLinkResponse sendGetRequest(String path, Map<String, String> query) {
        try {
            StringBuilder queryString = new StringBuilder();
            if (ObjectUtil.isNotEmpty(query)) {
                for (Map.Entry<String, String> entry : query.entrySet()) {
                    if (queryString.length() > 0) {
                        queryString.append("&");
                    }
                    queryString.append(entry.getKey()).append("=").append(URLUtil.encode(entry.getValue()));
                }
            }

            String url = properties.getEndpoint() + path;
            if (queryString.length() > 0) {
                url += "?" + queryString;
            }

            Map<String, String> headers = generateHeaders("GET", path, queryString.toString(), null);


            HttpRequest httpRequest = HttpRequest.get(url)
                    .addHeaders(headers)
                    .timeout(5000);

            Boolean verify = properties.getVerify();
            if (ObjectUtil.isNotEmpty(verify) && !verify) {
                setSslFactory(httpRequest);
            }

            HttpResponse response = httpRequest.execute();

            if (response.getStatus() != HttpStatus.HTTP_OK) {
                throw new WpsApiException("HTTP状态异常: " + response.getStatus());
            }

            WpsLinkResponse resp = JSONUtil.toBean(response.body(), WpsLinkResponse.class);
            if (resp.getCode() != 200) {
                throw new WpsApiException(resp.getCode(), resp.getMsg(), resp.getRequest_id());
            }
            return resp;

        } catch (Exception e) {
            throw new WpsApiException("WPS接口调用异常" + e.getMessage());
        }
    }

    private Map<String, String> generateHeaders(String method, String path, String queryString, byte[] body) {
        try {
            DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
            String date = df.format(new Date());

            String signPath = path;
            if (signPath.startsWith("/open")) {
                signPath = signPath.replaceFirst("/open", "");
            }
            if (queryString != null && !queryString.isEmpty()) {
                signPath += "?" + queryString;
            }

            String sha256body = (body != null && body.length > 0) ? DigestUtil.sha256Hex(body) : "";

            String signStr = "WPS-4" + method + signPath + "application/json" + date + sha256body;
            HMac mac = new HMac(HmacAlgorithm.HmacSHA256, properties.getSecretKey().getBytes(StandardCharsets.UTF_8));
            String signature = HexUtil.encodeHexStr(mac.digest(signStr.getBytes(StandardCharsets.UTF_8)));

            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("Wps-Docs-Date", date);
            headers.put("Wps-Docs-Authorization", String.format("WPS-4 %s:%s", properties.getAccessKey(), signature));
            return headers;
        } catch (Exception e) {
            throw new WpsApiException("生成 WPS-4 签名异常" + e.getMessage());
        }
    }

}
