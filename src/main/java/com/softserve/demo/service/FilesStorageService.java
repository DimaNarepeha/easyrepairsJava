package com.softserve.demo.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FilesStorageService {
    String getContentType(HttpServletRequest servletRequest,Resource resource, String name);

    String storeFile(MultipartFile file);

    Resource loadFile(String fileName);
}

