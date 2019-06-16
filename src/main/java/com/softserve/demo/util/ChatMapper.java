package com.softserve.demo.util;

import com.softserve.demo.dto.ChatDTO;
import com.softserve.demo.model.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMapper {
//    @Mapping(target = "sentBy", source = "sentBy")
//    @Mapping(target = "messageTo", source = "messageTo.id")
//    @Mapping(target = "messageFrom", source = "messageFrom.id")

            @Mapping(target = "customerId", source = "customer.id")
            @Mapping(target = "providerId", source = "provider.id")
    ChatDTO chatToChatDTO(Chat chat);


//    @Mapping(target = "sentBy", source = "sentBy")
//    @Mapping(target = "messageTo.id", source = "messageTo")
//    @Mapping(target = "messageFrom.id", source = "messageFrom")
            @Mapping(target = "customer.id", source = "customerId")
            @Mapping(target = "provider.id", source = "providerId")

    Chat chatDTOToChat(ChatDTO chatDTO);
}
