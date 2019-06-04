package com.softserve.demo.controller;

import com.softserve.demo.dto.LocationDTO;
import com.softserve.demo.dto.ProviderAndLocationDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.model.Provider;
import com.softserve.demo.model.ProviderStatus;
import com.softserve.demo.service.FilesStorageService;
import com.softserve.demo.service.ProvidersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Illia Chenchak
 */
@RestController
@RequestMapping("service-providers")
@CrossOrigin ("*")
public class ProvidersController {

    private final ProvidersService providersService;

    private final FilesStorageService fileStorageService;


    public ProvidersController(ProvidersService providersService, FilesStorageService fileStorageService) {
        this.providersService = providersService;
        this.fileStorageService = fileStorageService;
    }


    @PostMapping("save")
    public ResponseEntity<?> saveServiceProvider(@RequestBody ProviderDTO providerDTO) {
        return new ResponseEntity<>(providersService.save(providerDTO), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateServiceProviders(@RequestBody ProviderDTO providerDTO) {
        return new ResponseEntity<>(providersService.update(providerDTO), HttpStatus.OK);
    }

    @GetMapping("find-all")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(providersService.findAll(), HttpStatus.OK);
    }

    @GetMapping("find-all/page")
    public Page<?> getServiceProvidersByPage(@PageableDefault Pageable pageable) {
        return providersService.getServiceProvidersByPage(pageable);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteServiceProvidersResponse(@PathVariable("id") Integer id) {
        providersService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("find-by-id/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(providersService.findById(id), HttpStatus.OK);
    }

    @PostMapping("{userId}")
    public ResponseEntity<?> uploadImage(
            @PathVariable("userId") Integer id,
            @RequestParam("imageFile") MultipartFile file
    ) {
        fileStorageService.storeFile(file);
        providersService.addImageToProviders(id, file.getOriginalFilename());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/image/{imageName}")
    public ResponseEntity<?> getImageForProviders(@PathVariable("imageName") String name,
                                                  HttpServletRequest servletRequest) {
        Resource resource = fileStorageService.loadFile(name);
        String contentType = null;
        try {
            contentType = servletRequest
                    .getServletContext()
                    .getMimeType(
                            resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("find-all/status")
    public Page<?> getServiceProviderByStatus (@PageableDefault Pageable pageable,
                                               @RequestParam(defaultValue = "NOTAPPROVED") String status) {
        return providersService.getServiceProvidersByStatus(pageable, ProviderStatus.valueOf(status));
    }

    @PutMapping("update-status/{id}")
    public ResponseEntity<?> updateServiceProvidersStatus(@PathVariable("id") Integer id, @RequestBody String status) {
        return new ResponseEntity<>(providersService.updateStatus(id,status), HttpStatus.OK);
    }

    @GetMapping("find-by-userId/{id}")
    public ResponseEntity<?> findProviderByUserId(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(providersService.findProvidersByUserId(id), HttpStatus.OK);
    }


}
