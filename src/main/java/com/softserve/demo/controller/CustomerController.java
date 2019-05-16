package com.softserve.demo.controller;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.model.Customer;
import com.softserve.demo.service.CustomerService;
import com.softserve.demo.service.FilesStorageService;
import com.softserve.demo.util.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public CustomerController(CustomerService customerService, FilesStorageService fileStorageService) {
        this.customerService = customerService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(
            @RequestBody CustomerDTO customer) {
        customerService.createCustomer(CustomerMapper.INSTANCE.CustomerDTOToCustomer(customer));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        return new ResponseEntity<>(
                customerService.getAllCustomers().stream().map(
                        CustomerMapper.INSTANCE::CustomerToCustomerDTO)
                , HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> getCustomerById(@PathVariable("userId") Integer id) {
        return new ResponseEntity<>(
                CustomerMapper.INSTANCE.CustomerToCustomerDTO(customerService.getCustomerById(id)), HttpStatus.OK
        );
    }

    @GetMapping("list")
    public Page<CustomerDTO> getCustomersByPage(@PageableDefault Pageable pageable) {
        return customerService.getCustomersByPage(pageable)
                .map(CustomerMapper.INSTANCE::CustomerToCustomerDTO);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable("userId") Integer id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCustomer(
            @PathVariable("id") Integer id,
            @RequestBody Customer customer
    ) {
        CustomerDTO customerUpdated = CustomerMapper.INSTANCE.CustomerToCustomerDTO(customerService.updateCustomer(id, customer));

        if (customerUpdated == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400
        }

        return new ResponseEntity<>(customerUpdated, HttpStatus.OK); // 200
    }

    @PostMapping("{userId}/image")
    public ResponseEntity<?> uploadImage(
            @PathVariable("userId") Integer id,
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
