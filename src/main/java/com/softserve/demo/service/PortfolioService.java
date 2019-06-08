package com.softserve.demo.service;

import com.softserve.demo.dto.PortfolioDTO;
import com.softserve.demo.dto.PostDTO;
import com.softserve.demo.model.Portfolio;
import com.softserve.demo.model.Provider;

public interface PortfolioService {
    PortfolioDTO findById(Integer id);

    Portfolio createEmptyPortfolio(Provider provider);

    PostDTO findPostById(Integer id);
}
