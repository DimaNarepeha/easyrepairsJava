package com.softserve.demo.util;

import com.softserve.demo.dto.ServiceDTO;
import com.softserve.demo.model.Service;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface ServiceMapper {

    ServiceDTO ServiceToServiceDTO(Service service);

    Service ServiceDTOToService(ServiceDTO serviceDTO);

    List<ServiceDTO> ServicesToServiceDTOs(List<Service> services);

    List<Service> ServiceDTOsToService(List<ServiceDTO> services);
}
