package io.github.cookiegege.wpsOffice.utils;

import java.io.InputStream;

/**
 * @author JoSuper
 * @date 2026/1/8 16:59
 */
public interface UploadedFile {

    String getFileName();

    InputStream getInputStream() throws Exception;

    long getSize();

}
