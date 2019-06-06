package com.softserve.demo.service.impl;

import com.softserve.demo.dto.PortfolioDTO;
import com.softserve.demo.repository.PortfolioRepository;
import com.softserve.demo.service.PortfolioService;
import com.softserve.demo.util.PortfolioMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioMapper portfolioMapper;

    public PortfolioServiceImpl(PortfolioRepository portfolioRepository, PortfolioMapper portfolioMapper) {
        this.portfolioRepository = portfolioRepository;
        this.portfolioMapper = portfolioMapper;
    }


    @Override
    public PortfolioDTO findById(Integer id) {
        return portfolioMapper.portfolioToPortfolioDTO(portfolioRepository.findById(id).get());
    }
}
