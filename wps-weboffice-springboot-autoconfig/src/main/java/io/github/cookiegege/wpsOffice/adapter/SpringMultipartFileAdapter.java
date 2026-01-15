package io.github.cookiegege.wpsOffice.adapter;

import io.github.cookiegege.wpsOffice.utils.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author JoSuper
 * @date 2026/1/13 15:34
 */
public class SpringMultipartFileAdapter implements UploadedFile {

    private final MultipartFile multipartFile;

    public SpringMultipartFileAdapter(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    @Override
    public String getFileName() {
        return multipartFile.getOriginalFilename();
    }

    @Override
    public InputStream getInputStream() throws Exception {
        return multipartFile.getInputStream();
    }

    @Override
    public long getSize() {
        return multipartFile.getSize();
    }
}
