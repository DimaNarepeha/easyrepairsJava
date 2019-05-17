package com.softserve.demo.dto;

import com.softserve.demo.model.ProviderStatus;
import com.softserve.demo.model.Service;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProviderCriteriaDTO {

    private String city;
    private ProviderStatus status;
    private int minRaiting;
    private int maxRaiting;
    private List<Service> services = new ArrayList<>();

}
