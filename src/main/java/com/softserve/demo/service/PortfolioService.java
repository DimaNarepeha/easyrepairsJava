package com.softserve.demo.service;

import com.softserve.demo.dto.PortfolioDTO;

public interface PortfolioService {
    PortfolioDTO findById(Integer id);
}
