package com.softserve.demo.controller;

import com.softserve.demo.model.Customer;
import com.softserve.demo.repository.CustomerRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.CustomerService;
import com.softserve.demo.service.FilesStorageService;
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

@CrossOrigin("*")
@RestController
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    private FilesStorageService fileStorageService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;

//     @PostMapping("saveUserProfile")
//     public ResponseEntity<?> createCustomer(@RequestParam("file")MultipartFile multipartFile,
//        @RequestParam("user")String user
//     )
//         return new ResponseEntity<>(HttpStatus.CREATED);
//     }


    @PostMapping
    public ResponseEntity<?> createCustomer(
            @RequestBody Customer customer) {
        customer.setUser(userRepository.findById(customer.getUserId()));
        /*Customer customerE = new Customer();
        customerE.setFirstName(customer.getFirstName());
        customerE.setLastName(customer.getLastName());
        customerE.setImage(customer.getImage());
        customerE.setEmail(customer.getEmail());
        customerE.setUpdated(customer.getUpdated());
        customerE.setUserId(userRepository.findById(customer.getUserId()));*/
        customerService.createCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(
                customerService.getAllCustomers(), HttpStatus.OK
        );
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Integer id) {
        return new ResponseEntity<>(
                customerService.getCustomerById(id), HttpStatus.OK
        );
    }
    @GetMapping("list")
    public Page<Customer> getUsersByPage(@RequestParam(defaultValue = "0") int page)  {
        return customerService.getCustomersByPage(page);
    }


    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") Integer id) {
       customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable("id") Integer id,
            @RequestBody Customer customer
    ) {
        Customer customerUpdated= customerService.updateCustomer(id, customer);

        if (customerUpdated == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400
        }

        return new ResponseEntity<>(customerUpdated, HttpStatus.OK); // 200
    }
    @PostMapping("{userId}/image")
    public ResponseEntity<?> uploadImage(
            @PathVariable("userId")Integer id,
            @RequestParam("imageFile")MultipartFile file
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
