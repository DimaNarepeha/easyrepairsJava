package com.softserve.demo.controller;

import com.softserve.demo.model.ServiceProvider;
import com.softserve.demo.service.FilesStorageService;
import com.softserve.demo.service.ProvidersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Illia Chenchak
 */
@RestController
@RequestMapping("service-providers")
@CrossOrigin("*")
public class ProvidersController {

    @Autowired
    private ProvidersService providersService;

    @Autowired
    FilesStorageService fileStorageService;

    @PostMapping("save")
    public ResponseEntity<ServiceProvider> saveServiceProvider(@RequestBody ServiceProvider serviceProviders) {
        return new ResponseEntity<>(providersService.save(serviceProviders), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ServiceProvider> updateServiceProviders(@PathVariable("id") Integer id, @RequestBody ServiceProvider serviceProviders) {
        return new ResponseEntity<>(providersService.update(id, serviceProviders), HttpStatus.OK);
    }

    @GetMapping("find-all")
    public ResponseEntity<List<ServiceProvider>> findAll() {
        return new ResponseEntity<>(providersService.findAll(), HttpStatus.OK);
    }

    @GetMapping("find-all/page")
    public Page<ServiceProvider> getServiceProvidersByPage(@RequestParam(defaultValue = "0") int page)  {
        return providersService.getServiceProvidersByPage(page);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ServiceProvider> deleteServiceProvidersResponse(@PathVariable("id") Integer id) {
        providersService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("find-by-id/{id}")
    public ResponseEntity<ServiceProvider> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(providersService.findById(id), HttpStatus.OK);
    }

    @PostMapping("{userId}")
    public ResponseEntity<?> uploadImage(
            @PathVariable("userId") Integer id,
            @RequestParam("imageFile") MultipartFile file
    ) {
        System.out.println(file.getOriginalFilename());

        fileStorageService.storeFile(file);
        providersService.addImageToCustomer(id, file.getOriginalFilename());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/image/{imageName}")
    public ResponseEntity<?> getImage(
            @PathVariable("imageName") String name,
            HttpServletRequest servletRequest
    ) {

        Resource resource = fileStorageService.loadFile(name);

        String contentType = null;

        try {
            contentType = servletRequest
                    .getServletContext()
                    .getMimeType(
                            resource.getFile().getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}