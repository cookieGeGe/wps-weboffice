package io.github.cookiegege.wpsOffice.controller;

import io.github.cookiegege.wpsOffice.adapter.SpringMultipartFileAdapter;
import io.github.cookiegege.wpsOffice.autoconfigure.WpsProperties;
import io.github.cookiegege.wpsOffice.context.WpsCallbackContext;
import io.github.cookiegege.wpsOffice.context.WpsCallbackHeader;
import io.github.cookiegege.wpsOffice.dispatcher.WpsCallbackDispatcher;
import io.github.cookiegege.wpsOffice.model.*;
import io.github.cookiegege.wpsOffice.params.WpsUserInfoParam;
import io.github.cookiegege.wpsOffice.response.WpsFileResponse;
import io.github.cookiegege.wpsOffice.response.WpsHistoryResponse;
import io.github.cookiegege.wpsOffice.response.WpsUserInfoResponse;
import io.github.cookiegege.wpsOffice.spi.WpsCallbackService;
import io.github.cookiegege.wpsOffice.utils.WpsUtil;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author JoSuper
 * @date 2026/1/8 12:02
 */
@RestController
@ConditionalOnProperty(prefix = "wps.callback", name = "enabled", havingValue = "true", matchIfMissing = true)
@RequestMapping("${wps.callback.prefixUrl:/}")
public class WpsCallbackController {

    @Resource
    private WpsCallbackDispatcher wpsCallbackDispatcher;

    @Resource
    private WpsProperties wpsProperties;


    @GetMapping("/v1/3rd/file/info")
    public WpsFileInfo getFileInfo(
            WpsCallbackHeader header,
            @RequestParam Map<String, String> queryParams
    ) {
        WpsCallbackContext context = WpsUtil.convertToCallbackContext(queryParams, wpsProperties.getKeyPrefix());
        context.setHeader(header);
        WpsCallbackService service = wpsCallbackDispatcher.dispatch(context.getBizCode());
        return service.fileInfo(context);
    }

    @GetMapping("/v1/3rd/user/info")
    public WpsUserInfoResponse getUserInfo(
            WpsCallbackHeader header,
            @RequestParam Map<String, String> queryParams,
            @RequestBody WpsUserInfoParam wpsUserInfoParam
    ) {
        WpsCallbackContext context = WpsUtil.convertToCallbackContext(queryParams, wpsProperties.getKeyPrefix());
        context.setHeader(header);
        WpsCallbackService service = wpsCallbackDispatcher.dispatch(context.getBizCode());
        List<WpsUser> wpsUsers = service.userInfo(context, wpsUserInfoParam.getIds());
        WpsUserInfoResponse wpsUserInfoResponse = new WpsUserInfoResponse();
        wpsUserInfoResponse.setUsers(wpsUsers);
        return wpsUserInfoResponse;
    }

    @PostMapping("/v1/3rd/file/save")
    public WpsFileResponse saveFile(
            WpsCallbackHeader header,
            @RequestParam Map<String, String> queryParams,
            @RequestPart("file") MultipartFile file
    ) {
        WpsCallbackContext context = WpsUtil.convertToCallbackContext(queryParams, wpsProperties.getKeyPrefix());
        context.setHeader(header);
        WpsCallbackService service = wpsCallbackDispatcher.dispatch(context.getBizCode());

        SpringMultipartFileAdapter uploadFile = new SpringMultipartFileAdapter(file);

        WpsFile wpsFile = service.fileSave(context, uploadFile);

        WpsFileResponse wpsFileSaveResponse = new WpsFileResponse();
        wpsFileSaveResponse.setFile(wpsFile);
        return wpsFileSaveResponse;
    }

    @PutMapping("/v1/3rd/file/rename")
    public void renameFile(
            WpsCallbackHeader header,
            @RequestParam Map<String, String> queryParams,
            @RequestBody Map<String, String> body
    ) {
        String newName = body.get("name");
        WpsCallbackContext context = WpsUtil.convertToCallbackContext(queryParams, wpsProperties.getKeyPrefix());
        context.setHeader(header);
        WpsCallbackService service = wpsCallbackDispatcher.dispatch(context.getBizCode());
        service.rename(context, newName);
    }

    @PostMapping("/v1/3rd/file/online")
    public void onlineFile(
            WpsCallbackHeader header,
            @RequestParam Map<String, String> queryParams,
            @RequestBody WpsUserInfoParam wpsUserInfoParam
    ) {
        List<String> ids = wpsUserInfoParam.getIds();
        WpsCallbackContext context = WpsUtil.convertToCallbackContext(queryParams, wpsProperties.getKeyPrefix());
        context.setHeader(header);
        WpsCallbackService service = wpsCallbackDispatcher.dispatch(context.getBizCode());
        service.fileOnline(context, ids);
    }

    // ----------------- 6. 回调通知 -----------------
    @PostMapping("/v1/3rd/onnotify")
    public void onNotify(
            WpsCallbackHeader header,
            @RequestParam Map<String, String> queryParams,
            @RequestBody WpsNotifyBody wpsNotifyBody
    ) {
        WpsCallbackContext context = WpsUtil.convertToCallbackContext(queryParams, wpsProperties.getKeyPrefix());
        context.setHeader(header);
        WpsCallbackService service = wpsCallbackDispatcher.dispatch(context.getBizCode());
        service.fileOnNotify(context, wpsNotifyBody);
    }

    // ----------------- 7. 获取特定版本文件信息 -----------------
    @GetMapping("/v1/3rd/file/version/{version}")
    public WpsFileResponse getFileVersion(
            WpsCallbackHeader header,
            @RequestParam Map<String, String> queryParams,
            @PathVariable("version") String version
    ) {
        WpsCallbackContext context = WpsUtil.convertToCallbackContext(queryParams, wpsProperties.getKeyPrefix());
        context.setHeader(header);
        WpsCallbackService service = wpsCallbackDispatcher.dispatch(context.getBizCode());
        WpsFile wpsFile = service.fileVersion(context, version);
        WpsFileResponse fileResponse = new WpsFileResponse();
        fileResponse.setFile(wpsFile);
        return fileResponse;
    }

    // ----------------- 8. 获取历史版本文件信息 -----------------
    @PostMapping("/v1/3rd/file/history")
    public WpsHistoryResponse getFileHistory(
            WpsCallbackHeader header,
            @RequestParam Map<String, String> queryParams,
            @RequestBody WpsHistoryBody wpsHistoryBody
    ) {

        WpsCallbackContext context = WpsUtil.convertToCallbackContext(queryParams, wpsProperties.getKeyPrefix());
        context.setHeader(header);
        WpsCallbackService service = wpsCallbackDispatcher.dispatch(context.getBizCode());
        List<WpsHistoryItem> histories = service.fileHistory(context, wpsHistoryBody);
        WpsHistoryResponse wpsHistoryResponse = new WpsHistoryResponse();
        wpsHistoryResponse.setHistories(histories);
        return wpsHistoryResponse;
    }


}
