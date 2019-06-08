package com.softserve.demo.util.mappers;

import com.softserve.demo.dto.PortfolioDTO;
import com.softserve.demo.model.Portfolio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PortfolioMapper {

    @Mappings({
            @Mapping(target = "providerId", source = "provider.id"),
            @Mapping(target = "postDTOs", source = "posts")

    })
    PortfolioDTO portfolioToPortfolioDTO(Portfolio portfolio);
}
