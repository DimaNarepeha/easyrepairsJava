package com.softserve.demo.util;

import com.softserve.demo.dto.ChatDTO;
import com.softserve.demo.model.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMapper {

            @Mapping(target = "customerId", source = "customer.id")
            @Mapping(target = "providerId", source = "provider.id")
    ChatDTO chatToChatDTO(Chat chat);

            @Mapping(target = "customer.id", source = "customerId")
            @Mapping(target = "provider.id", source = "providerId")

    Chat chatDTOToChat(ChatDTO chatDTO);
}
