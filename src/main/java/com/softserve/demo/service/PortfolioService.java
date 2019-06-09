package com.softserve.demo.service;

import com.softserve.demo.dto.PortfolioDTO;
import com.softserve.demo.dto.PostDTO;
import com.softserve.demo.model.Portfolio;
import com.softserve.demo.model.Provider;

public interface PortfolioService {
    PortfolioDTO findByProviderId(Integer id);

    Portfolio createEmptyPortfolio(Provider provider);

    PostDTO findPostById(Integer id);

    void addImageToPost(Integer id, String originalFilename);

    PostDTO updatePost(PostDTO postDTO, Integer id);

    PostDTO createPost(PostDTO postDTO);

    void deletePost(Integer postId);
}
