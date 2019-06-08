package com.softserve.demo.controller;

import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.model.ProviderStatus;
import com.softserve.demo.service.FilesStorageService;
import com.softserve.demo.service.ProvidersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("service-providers")
@CrossOrigin("*")
public class ProvidersController {

    private final ProvidersService providersService;

    private final FilesStorageService fileStorageService;


    public ProvidersController(ProvidersService providersService, FilesStorageService fileStorageService) {
        this.providersService = providersService;
        this.fileStorageService = fileStorageService;
    }


    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    public ProviderDTO saveServiceProvider(@RequestBody ProviderDTO providerDTO) {
        return providersService.save(providerDTO);
    }

    @PutMapping("/update")
    public ProviderDTO updateServiceProviders(@RequestBody ProviderDTO providerDTO) {
        return providersService.update(providerDTO);
    }

    @GetMapping("find-all")
    public List<ProviderDTO> findAll() {
        return providersService.findAll();
    }

    @GetMapping("find-all/page")
    public Page<?> getServiceProvidersByPage(@PageableDefault Pageable pageable) {
        return providersService.getServiceProvidersByPage(pageable);
    }

    @DeleteMapping("delete/{id}")
    public void deleteServiceProvidersResponse(@PathVariable("id") Integer idProvider) {
        providersService.delete(idProvider);
    }

    @GetMapping("find-by-id/{id}")
    public ProviderDTO findById(@PathVariable("id") Integer idProvider) {
        return providersService.findById(idProvider);
    }

    @PostMapping("{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void uploadImage(
            @PathVariable("userId") Integer idUser,
            @RequestParam("imageFile") MultipartFile file
    ) {
        fileStorageService.storeFile(file);
        providersService.addImageToProviders(idUser, file.getOriginalFilename());
    }


    @GetMapping("find-all/status")
    public Page<?> getServiceProviderByStatus(@PageableDefault Pageable pageable,
                                              @RequestParam(defaultValue = "NOTAPPROVED") String status) {
        return providersService.getServiceProvidersByStatus(pageable, ProviderStatus.valueOf(status));
    }

    @PutMapping("update-status/{id}")
    public ProviderDTO updateServiceProvidersStatus(@PathVariable("id") Integer idProvider, @RequestBody String status) {
        return providersService.updateStatus(idProvider, status);
    }

    @GetMapping("find-by-userId/{id}")
    public ProviderDTO findProviderByUserId(@PathVariable("id") Integer idUser) {
        return providersService.findProvidersByUserId(idUser);
    }

    @GetMapping("by/{name}")
    public ProviderDTO findByName(@PathVariable("name") String providerName) {
        return providersService.findByName(providerName);
    }
}
