package io.github.cookiegege.wpsOffice.response;

import io.github.cookiegege.wpsOffice.model.WpsHistoryItem;
import lombok.Data;

import java.util.List;

/**
 * @author JoSuper
 * @date 2026/1/13 15:48
 */
@Data
public class WpsHistoryResponse {

    List<WpsHistoryItem> histories;


}
