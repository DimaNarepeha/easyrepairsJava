package com.softserve.demo.controller;

import com.softserve.demo.dto.PortfolioDTO;
import com.softserve.demo.service.PortfolioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("provider-portfolio")
@CrossOrigin("*")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("{id}")
    public PortfolioDTO getPortfolio(@PathVariable Integer id){
        return portfolioService.findById(id);
    }




}
