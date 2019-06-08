/*
 *Open source 2019
 */
package com.softserve.demo.util.mappers;

import com.softserve.demo.dto.NotificationDTO;
import com.softserve.demo.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    @Mapping(source = "time", target = "time")
    NotificationDTO notificationToNotificationDTO(Notification notification);

    Notification notificationDTOToNotification(NotificationDTO notificationDTO);

    List<NotificationDTO> notificationsToNotificationDTOs(List<Notification> notifications);
}
