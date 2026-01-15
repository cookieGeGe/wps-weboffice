package io.github.cookiegege.wpsOffice.response;

import io.github.cookiegege.wpsOffice.model.WpsUser;
import lombok.Data;

import java.util.List;

/**
 * @author JoSuper
 * @date 2026/1/13 15:23
 */
@Data
public class WpsUserInfoResponse {

    List<WpsUser> users;


}
