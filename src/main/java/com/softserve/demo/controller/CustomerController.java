package com.softserve.demo.controller;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.dto.ProviderDTO;
import com.softserve.demo.filter.CustomerFilter;
import com.softserve.demo.filter.ProviderFilter;
import com.softserve.demo.model.CustomerStatus;
import com.softserve.demo.model.ProviderStatus;
import com.softserve.demo.service.CustomerService;
import com.softserve.demo.service.FilesStorageService;
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
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("customers")
public class CustomerController {
    private final CustomerService customerService;
    private final FilesStorageService fileStorageService;
    private final CustomerFilter customerFilter;


    public CustomerController(final CustomerService customerService, final FilesStorageService fileStorageService,
                              final CustomerFilter customerFilter) {
        this.customerService = customerService;
        this.customerFilter = customerFilter;
        this.fileStorageService = fileStorageService;
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    public CustomerDTO getCustomerById(@PathVariable("id") Integer id) {
        return customerService.getCustomerById(id);

    }

    @GetMapping("list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    public Page<CustomerDTO> getCustomersByPage(@PageableDefault Pageable pageable) {
        return customerService.getCustomersByPage(pageable);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    public void deleteCustomerById(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    public CustomerDTO updateCustomer(
            @RequestBody CustomerDTO customer
    ) {
        return customerService.updateCustomer(customer);
    }

    @PostMapping("{id}/image")
    public void uploadImage(
            @PathVariable("id") Integer id,
            @RequestParam("imageFile") MultipartFile file
    ) {

        fileStorageService.storeFile(file);
        customerService.addImageToCustomer(id, file.getOriginalFilename());
    }

    @GetMapping("image/{imageName}")
    public ResponseEntity<?> getImage(
            @PathVariable("imageName") String name,
            final HttpServletRequest servletRequest
    ) {
        Resource resource = fileStorageService.loadFile(name);
        String contentType = fileStorageService.getContentType(servletRequest, resource, name);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    @GetMapping("find-by-userId/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    public CustomerDTO getCustomerByUserId(@PathVariable("id") Integer id) {
        return customerService.getCustomerByUserId(id);
    }

    @GetMapping("status")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Page<?> getCustomersByStatus(@PageableDefault Pageable pageable,
                                        @RequestParam String status) {
        return customerService.getCustomersByStatus(pageable, CustomerStatus.valueOf(status));
    }

    @PutMapping("update-status/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CustomerDTO updateCustomerStatus(@PathVariable("id") Integer id, @RequestBody String status) {
        return customerService.updateStatus(id, status);
    }

    @GetMapping("status/searchByFirstName")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<?> getCustomersByFirstName(@RequestParam int page,
                                           @RequestParam int pageSize,
                                           @RequestParam String status,
                                           @RequestParam String firstName) {
        return customerFilter.firstNameLike(page,pageSize, firstName, CustomerStatus.valueOf(status));
    }

    @GetMapping("status/searchByLastName")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<?> getCustomersByLastName(@RequestParam int page,
                                          @RequestParam int pageSize,
                                          @RequestParam String status,
                                          @RequestParam String lastName) {
        return customerFilter.lastNameLike(page,pageSize, lastName, CustomerStatus.valueOf(status));
    }
}
