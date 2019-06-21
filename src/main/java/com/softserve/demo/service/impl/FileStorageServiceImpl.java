package com.softserve.demo.service.impl;

import com.softserve.demo.service.FilesStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
@Slf4j
public class FileStorageServiceImpl implements FilesStorageService {


    private final String PATH = System.getProperty("user.dir");
    private final String SEPARATOR = System.getProperty("file.separator");


    private final Path fileStorageLocation;

    public FileStorageServiceImpl() {
        String uploadDir = PATH + SEPARATOR + "uploads";


        this.fileStorageLocation = Paths.get(uploadDir)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }

    @Override
    public String storeFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        Path targetLocation = null;

        try {
            targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return fileName;
    }

    @Override
    public Resource loadFile(String fileName) {

        Path filePath = this.fileStorageLocation.resolve(fileName);

        try {
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            }
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
        }

        return null;
    }

    @Override
    public String getContentType(HttpServletRequest servletRequest, Resource resource, String name) {
        return "application/octet-stream";
    }
}
