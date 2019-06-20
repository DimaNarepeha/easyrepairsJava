package com.softserve.demo.util;

import com.softserve.demo.dto.ChatDTO;
import com.softserve.demo.model.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    ChatDTO chatToChatDTO(Chat chat);

    Chat chatDTOToChat(ChatDTO chatDTO);
}
