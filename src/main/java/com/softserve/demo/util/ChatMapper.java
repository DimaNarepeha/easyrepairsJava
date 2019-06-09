package com.softserve.demo.util;

import com.softserve.demo.dto.ChatDTO;
import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.model.Chat;
import com.softserve.demo.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    @Mappings({
            @Mapping(target = "message", source = "message"),
            @Mapping(target = "sentBy", source = "sentBy")
    })
    ChatDTO chatToChatDTO(Chat chat);

    @Mappings({
            @Mapping(target = "message", source = "message"),
            @Mapping(target = "sentBy", source = "sentBy")
    })
    Chat chatDTOToChat(ChatDTO chatDTO);
}
