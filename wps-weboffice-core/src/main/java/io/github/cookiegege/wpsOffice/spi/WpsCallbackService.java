package io.github.cookiegege.wpsOffice.spi;

import io.github.cookiegege.wpsOffice.context.WpsCallbackContext;
import io.github.cookiegege.wpsOffice.model.*;
import io.github.cookiegege.wpsOffice.utils.UploadedFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JoSuper
 * @date 2026/1/8 11:31
 */
public interface WpsCallbackService {


    WpsFileInfo fileInfo(WpsCallbackContext wpsCallbackContext);


    List<WpsUser> userInfo(WpsCallbackContext wpsCallbackContext, List<String> userIdList);


    WpsFile fileSave(WpsCallbackContext wpsCallbackContext, UploadedFile uploadedFile);

    default void rename(WpsCallbackContext wpsCallbackContext, String newName) {
    }

    ;

    default void fileOnline(WpsCallbackContext wpsCallbackContext, List<String> userIdList) {
    }

    ;

    default void fileOnNotify(WpsCallbackContext wpsCallbackContext, WpsNotifyBody wpsNotifyBody) {
    }

    ;

    default WpsFile fileVersion(WpsCallbackContext wpsCallbackContext, String version) {
        return null;
    }

    ;

    default List<WpsHistoryItem> fileHistory(WpsCallbackContext wpsCallbackContext, WpsHistoryBody wpsHistoryBody) {
        return new ArrayList<>();
    }

}
