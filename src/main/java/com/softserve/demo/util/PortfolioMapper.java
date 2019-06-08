package com.softserve.demo.util;

import com.softserve.demo.dto.PortfolioDTO;
import com.softserve.demo.model.Portfolio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PortfolioMapper {

    PortfolioDTO portfolioToPortfolioDTO(Portfolio portfolio);

    Portfolio portfolioDTOToPortfolio(PortfolioDTO portfolioDTO);
}
