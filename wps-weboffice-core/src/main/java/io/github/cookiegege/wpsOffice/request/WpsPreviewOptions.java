package io.github.cookiegege.wpsOffice.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoSuper
 * @date 2026/1/13 10:23
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WpsPreviewOptions {

    private boolean formatRevision;
    private boolean insertDelete;
    private boolean comment;
    private boolean embeddedMode;
    private boolean bubbleMode;
    private boolean mark;
    private boolean originalState;

    public String toBitString() {
        return (formatRevision?"1":"0") +
                (insertDelete?"1":"0") +
                (comment?"1":"0") +
                (embeddedMode?"1":"0") +
                (bubbleMode?"1":"0") +
                (mark?"1":"0") +
                (originalState?"1":"0");
    }

}
