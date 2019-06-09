package com.softserve.demo.util.mappers;

import com.softserve.demo.dto.ServiceDTO;
import com.softserve.demo.model.Service;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface ServiceMapper {

    ServiceDTO serviceToServiceDTO(Service service);

    Service serviceDTOToService(ServiceDTO serviceDTO);

    List<ServiceDTO> servicesToServiceDTOs(List<Service> services);

    List<Service> serviceDTOsToService(List<ServiceDTO> services);
}
