package com.softserve.demo.controller;

import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.filter.ProviderFilter;
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

/**
 * Created by Illia Chenchak
 */
@RestController
@RequestMapping("service-providers")
@CrossOrigin("*")
public class ProvidersController {

    private final ProvidersService providersService;

    private final FilesStorageService fileStorageService;
    private final ProviderFilter providerFilter;


    public ProvidersController(ProvidersService providersService, FilesStorageService fileStorageService, ProviderFilter providerFilter) {
        this.providersService = providersService;
        this.fileStorageService = fileStorageService;
        this.providerFilter = providerFilter;
    }


    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    public ProviderDTO saveServiceProvider(@RequestBody ProviderDTO providerDTO) {
        return providersService.save(providerDTO);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
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
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteServiceProvidersResponse(@PathVariable("id") Integer id) {
        providersService.delete(id);
    }

    @GetMapping("find-by-id/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER','PROVIDER')")
    public ProviderDTO findById(@PathVariable("id") Integer id) {
        return providersService.findById(id);
    }

    @PostMapping("{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void uploadImage(
            @PathVariable("userId") Integer id,
            @RequestParam("imageFile") MultipartFile file
    ) {
        fileStorageService.storeFile(file);
        providersService.addImageToProviders(id, file.getOriginalFilename());
    }


    @GetMapping("find-all/status")
    public Page<?> getServiceProviderByStatus(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam int numberOfProvidersOnPage,
                                              @RequestParam(defaultValue = "NOTAPPROVED") String status) {
        return providersService.getServiceProvidersByStatus(page,numberOfProvidersOnPage, ProviderStatus.valueOf(status));
    }

    @GetMapping("find-all/searchByName")
    @ResponseStatus(HttpStatus.OK)

    public Page<ProviderDTO> getServiceProviderByName(@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam int numberOfProvidersOnPage,
                                                                       @RequestParam(defaultValue = "NOTAPPROVED") String status,
                                                                       @RequestParam String searchName) {
        return providerFilter.nameLike(page, numberOfProvidersOnPage, searchName, ProviderStatus.valueOf(status));

    }

    @PutMapping("update-status/{id}")
    public ProviderDTO updateServiceProvidersStatus(@PathVariable("id") Integer id, @RequestBody String status) {
        return providersService.updateStatus(id, status);
    }

    @GetMapping("find-by-userId/{id}")
    public ProviderDTO findProviderByUserId(@PathVariable("id") Integer id) {
        return providersService.findProvidersByUserId(id);
    }

    @GetMapping("by/{name}")
    public ProviderDTO findByName(@PathVariable("name") String name) {
        return providersService.findByName(name);
    }
}
