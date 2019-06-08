package com.softserve.demo.controller;

import com.softserve.demo.dto.CustomerDTO;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("customers")
public class CustomerController {
    private final CustomerService customerService;
    private final FilesStorageService fileStorageService;


    public CustomerController(final CustomerService customerService, final FilesStorageService fileStorageService) {
        this.customerService = customerService;
        this.fileStorageService = fileStorageService;
    }


    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        return new ResponseEntity<>(
                customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(
                customerService.getCustomerById(id), HttpStatus.OK
        );
    }


    @GetMapping("list")
    public Page<CustomerDTO> getCustomersByPage(@PageableDefault Pageable pageable) {
        return customerService.getCustomersByPage(pageable);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(
            @RequestBody CustomerDTO customer
    ) {
        CustomerDTO customerUpdated = customerService.updateCustomer(customer);

        return new ResponseEntity<>(customerUpdated, HttpStatus.OK);
    }

    @PostMapping("{id}/image")
    public ResponseEntity<?> uploadImage(
            @PathVariable("id") Integer id,
            @RequestParam("imageFile") MultipartFile file
    ) {
        System.out.println(file.getOriginalFilename());

        fileStorageService.storeFile(file);
        customerService.addImageToCustomer(id, file.getOriginalFilename());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("image/{imageName}")
    public ResponseEntity<?> getImage(
            @PathVariable("imageName") String name,
            final HttpServletRequest servletRequest
    ) {

        Resource resource = fileStorageService.loadFile(name);
        String contentType = fileStorageService.getContentType(servletRequest,resource,name);


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("find-by-userId/{id}")
    public ResponseEntity<?> findCustomerByUserId(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(customerService.findCustomerByUserId(id), HttpStatus.OK);
    }

}
