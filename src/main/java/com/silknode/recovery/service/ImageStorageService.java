package com.silknode.recovery.service;

import com.silknode.recovery.config.ImageStorageProperties;
import com.silknode.recovery.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageStorageService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ImageStorageService.class);

    private final ImageStorageProperties properties;

    public ImageStorageService(ImageStorageProperties properties) {
        this.properties = properties;
    }

    public String storeImage(MultipartFile file) throws IOException {
        String uploadDir = properties.getDir();
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            log.info("Creating upload directory: {}", uploadDir);
            dir.mkdirs();
        }
        String filePath = uploadDir + File.separator + FileUtils.sanitizeFilename(file.getOriginalFilename());
        file.transferTo(new File(filePath));
        log.info("Archivo copiado a {}", filePath);
        return filePath;
    }
}