package com.softserve.demo.service.impl;

import com.softserve.demo.dto.PortfolioDTO;
import com.softserve.demo.dto.PostDTO;
import com.softserve.demo.model.Portfolio;
import com.softserve.demo.model.Post;
import com.softserve.demo.model.Provider;
import com.softserve.demo.repository.PortfolioRepository;
import com.softserve.demo.repository.PostRepository;
import com.softserve.demo.repository.UserRepository;
import com.softserve.demo.service.PortfolioService;
import com.softserve.demo.util.mappers.PortfolioMapper;
import com.softserve.demo.util.mappers.PostMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@Slf4j
@Transactional
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioMapper portfolioMapper;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PortfolioServiceImpl(PortfolioRepository portfolioRepository, PortfolioMapper portfolioMapper, UserRepository userRepository, PostRepository postRepository, PostMapper postMapper) {
        this.portfolioRepository = portfolioRepository;
        this.portfolioMapper = portfolioMapper;
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public PortfolioDTO findById(Integer id) {
        return convertPortfolioToPortfolioDTO(portfolioRepository.findById(id).get());
    }

    @Override
    public Portfolio createEmptyPortfolio(Provider provider) {
        Portfolio portfolio = new Portfolio();
        portfolio.setProvider(provider);
        return portfolioRepository.save(portfolio);
    }

    @Override
    public PostDTO findPostById(Integer id) {
        return postMapper.postToPostDTO(postRepository.findById(id).get());
    }

    @Override
    public void addImageToPost(Integer id, String originalFilename) {
        Post post = postRepository.findById(id).get();
        post.setMainPhoto(originalFilename);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer id) {
        Post post = postRepository.findById(id).get();
        Portfolio portfolio = portfolioRepository.findById(postDTO.getPortfolioId()).get();
        portfolio.setLastUpdate(LocalDateTime.now());
        post.setHeader(postDTO.getHeader());
        post.setMainDescription(postDTO.getMainDescription());
        return postDTO;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = postMapper.postDTOToPost(postDTO);
        Portfolio portfolio = portfolioRepository.findById(postDTO.getPortfolioId()).get();
        portfolio.setLastUpdate(LocalDateTime.now());
        post.setPortfolio(portfolio);
        return postMapper.postToPostDTO(postRepository.save(post));
    }

    @Override
    public void deletePost(Integer postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PortfolioDTO findByProviderId(Integer providerId) {
        return portfolioMapper.portfolioToPortfolioDTO(portfolioRepository.findByProviderId(providerId));
    }

    private PortfolioDTO convertPortfolioToPortfolioDTO(Portfolio portfolio) {
        PortfolioDTO portfolioDTO = portfolioMapper.portfolioToPortfolioDTO(portfolio);
        for (PostDTO postDTO : portfolioDTO.getPostDTOs()) {
            postDTO.setPortfolioId(portfolio.getId());
        }
        return portfolioDTO;
    }
}
