package com.softserve.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProviderCriteriaDTO {

    private String location;
    private int minRating;
    private List<ServiceDTO> services = new ArrayList<>();

}
