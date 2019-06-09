package com.softserve.demo.controller;

import com.softserve.demo.dto.PortfolioDTO;
import com.softserve.demo.dto.PostDTO;
import com.softserve.demo.service.FilesStorageService;
import com.softserve.demo.service.PortfolioService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("provider-portfolio")
@CrossOrigin("*")
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final FilesStorageService fileStorageService;

    public PortfolioController(PortfolioService portfolioService, FilesStorageService filesStorageService) {
        this.portfolioService = portfolioService;
        this.fileStorageService = filesStorageService;
    }

    @GetMapping("provider/{providerId}")
    public PortfolioDTO getPortfolioIdByProviderId(@PathVariable("providerId") Integer providerId){
        return portfolioService.findByProviderId(providerId);
    }

    @GetMapping("{portfolioId}")
    public PortfolioDTO getPortfolioByProviderId(@PathVariable("portfolioId") Integer portfolioId){
        return portfolioService.findById(portfolioId);
    }

    @GetMapping("/post/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROVIDER')")
    public PostDTO getPost(@PathVariable("id") Integer id){
        return portfolioService.findPostById(id);
    }

    @PutMapping("/post/{postId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROVIDER')")
    public PostDTO updatePost(@PathVariable("postId") Integer postId, @RequestBody PostDTO postDTO){
        return portfolioService.updatePost(postDTO,postId);
    }

    @PostMapping("/post")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROVIDER')")
    public PostDTO createPost(@RequestBody PostDTO postDTO){
        return portfolioService.createPost(postDTO);
    }

    @DeleteMapping("/post/{postId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROVIDER')")
    public void deletePost(@PathVariable("postId") Integer postId){
        portfolioService.deletePost(postId);
    }

    @PostMapping("/post/image/{postId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROVIDER')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void uploadImage(
            @PathVariable("postId") Integer id,
            @RequestParam("imageFile") MultipartFile file
    ) {
        fileStorageService.storeFile(file);
        portfolioService.addImageToPost(id, file.getOriginalFilename());
    }
}
