package com.softserve.demo.service.impl;

import com.softserve.demo.dto.PortfolioDTO;
import com.softserve.demo.dto.PostDTO;
import com.softserve.demo.model.Portfolio;
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
        return convertPortfolioToPortfolioDTO(portfolioRepository.findByProviderId(id));
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

    private PortfolioDTO convertPortfolioToPortfolioDTO(Portfolio portfolio){
        PortfolioDTO portfolioDTO = portfolioMapper.portfolioToPortfolioDTO(portfolio);
        for (PostDTO postDTO : portfolioDTO.getPostDTOs()) {
            postDTO.setPortfolioId(portfolio.getId());
        }
        return  portfolioDTO;
    }
}
