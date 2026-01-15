package io.github.cookiegege.wpsOffice.model;

import lombok.Data;

/**
 * @author JoSuper
 * @date 2026/1/8 13:53
 */
@Data
public class WpsFileInfo {

    private WpsFile file;
    private WpsUser user;

    public static WpsFileInfo newInstance() {
        WpsFileInfo info = new WpsFileInfo();
        info.setFile(WpsFile.newInstance());
        info.setUser(new WpsUser());
        return info;
    }


}
