package com.github.FEBackEnd.shared.upload.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface Storable {
    void init();
    void save(MultipartFile file);

    Resource load(String filename);
}
