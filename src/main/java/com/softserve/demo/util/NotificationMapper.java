/*
 *Open source 2019
 */
package com.softserve.demo.util;

import com.softserve.demo.dto.NotificationDTO;
import com.softserve.demo.model.Notification;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface NotificationMapper {

    NotificationDTO NotificationToNotificationDTO(Notification notification);

    Notification NotificationDTOToNotification(NotificationDTO notificationDTO);

    List<NotificationDTO> NotificationsToNotificationDTOs(List<Notification> notifications);
}
