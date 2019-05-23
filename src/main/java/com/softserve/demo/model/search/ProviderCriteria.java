package com.softserve.demo.model.search;

import com.softserve.demo.model.Service;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProviderCriteria {

    private String location;
    private int minRating;
    private List<Service> services = new ArrayList<>();


}
