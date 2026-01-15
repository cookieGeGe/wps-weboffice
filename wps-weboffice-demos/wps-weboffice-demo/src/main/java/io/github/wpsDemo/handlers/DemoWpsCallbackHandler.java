package io.github.wpsDemo.handlers;

import io.github.cookiegege.wpsOffice.annotation.WpsCallbackProvider;
import io.github.wpsDemo.constants.BizCodeConstant;
import io.github.cookiegege.wpsOffice.context.WpsCallbackContext;
import io.github.cookiegege.wpsOffice.model.WpsFile;
import io.github.cookiegege.wpsOffice.model.WpsHistoryItem;
import io.github.cookiegege.wpsOffice.model.WpsFileInfo;
import io.github.cookiegege.wpsOffice.model.WpsHistoryBody;
import io.github.cookiegege.wpsOffice.model.WpsNotifyBody;
import io.github.cookiegege.wpsOffice.model.WpsUser;
import io.github.cookiegege.wpsOffice.spi.WpsCallbackService;
import io.github.cookiegege.wpsOffice.utils.UploadedFile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JoSuper
 * @date 2026/1/14 9:18
 */
@Service
@WpsCallbackProvider(bizCode = BizCodeConstant.DEMO)
public class DemoWpsCallbackHandler implements WpsCallbackService {

    @Override
    public WpsFileInfo fileInfo(WpsCallbackContext wpsCallbackContext) {
        System.out.println("====================fileInfo====================");
        System.out.println(BizCodeConstant.DEMO);
        System.out.println(wpsCallbackContext);
        System.out.println("====================fileInfo====================");
        return WpsFileInfo.newInstance();
    }

    @Override
    public List<WpsUser> userInfo(WpsCallbackContext wpsCallbackContext, List<String> userIdList) {
        System.out.println("====================userInfo====================");
        System.out.println(BizCodeConstant.DEMO);
        System.out.println(wpsCallbackContext);
        System.out.println(userIdList);
        System.out.println("====================userInfo====================");
        ArrayList<WpsUser> list = new ArrayList<>();
        list.add(new WpsUser());
        return list;
    }

    @Override
    public WpsFile fileSave(WpsCallbackContext wpsCallbackContext, UploadedFile uploadedFile) {
        System.out.println("====================fileSave====================");
        System.out.println(BizCodeConstant.DEMO);
        System.out.println(wpsCallbackContext);
        System.out.println(uploadedFile);
        System.out.println("====================fileSave====================");
        return WpsFile.newInstance();
    }

    @Override
    public void rename(WpsCallbackContext wpsCallbackContext, String newName) {
        System.out.println("====================rename====================");
        System.out.println(BizCodeConstant.DEMO);
        System.out.println(wpsCallbackContext);
        System.out.println(newName);
        System.out.println("====================rename====================");
        WpsCallbackService.super.rename(wpsCallbackContext, newName);
    }

    @Override
    public void fileOnline(WpsCallbackContext wpsCallbackContext, List<String> userIdList) {
        System.out.println("====================fileOnline====================");
        System.out.println(BizCodeConstant.DEMO);
        System.out.println(wpsCallbackContext);
        System.out.println(userIdList);
        System.out.println("====================fileOnline====================");
        WpsCallbackService.super.fileOnline(wpsCallbackContext, userIdList);
    }

    @Override
    public void fileOnNotify(WpsCallbackContext wpsCallbackContext, WpsNotifyBody wpsNotifyBody) {
        System.out.println("====================fileOnNotify====================");
        System.out.println(BizCodeConstant.DEMO);
        System.out.println(wpsCallbackContext);
        System.out.println(wpsNotifyBody);
        System.out.println("====================fileOnNotify====================");
        WpsCallbackService.super.fileOnNotify(wpsCallbackContext, wpsNotifyBody);
    }

    @Override
    public WpsFile fileVersion(WpsCallbackContext wpsCallbackContext, String version) {
        System.out.println("====================fileVersion====================");
        System.out.println(BizCodeConstant.DEMO);
        System.out.println(wpsCallbackContext);
        System.out.println(version);
        System.out.println("====================fileVersion====================");
        return WpsFile.newInstance();
    }

    @Override
    public List<WpsHistoryItem> fileHistory(WpsCallbackContext wpsCallbackContext, WpsHistoryBody wpsHistoryBody) {
        System.out.println("====================fileHistory====================");
        System.out.println(BizCodeConstant.DEMO);
        System.out.println(wpsCallbackContext);
        System.out.println(wpsHistoryBody);
        System.out.println("====================fileHistory====================");
        ArrayList<WpsHistoryItem> list = new ArrayList<>();
        list.add(WpsHistoryItem.newInstance());
        return list;
    }
}
