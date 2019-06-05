package com.softserve.demo.util;

import com.softserve.demo.dto.FeedbackGeneralDTO;
import com.softserve.demo.model.Feedback;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedbackGeneralMapper {

            @Mapping(target = "id", source = "entity.id")
            @Mapping(target = "comment", source = "entity.comment")
            @Mapping(target = "rating", source = "entity.rating")
            @Mapping(target = "username", source = "entity.addressedFrom.username")
            @Mapping(target = "image", source = "entity.addressedFrom.image")
    @Named("toDto")
    FeedbackGeneralDTO convertToDTO(Feedback entity);

            @Mapping(target = "id", source = "dto.id")
            @Mapping(target = "comment", source = "dto.comment")
            @Mapping(target = "rating", source = "dto.rating")
    Feedback convertToEntity(FeedbackGeneralDTO dto);

    @IterableMapping(qualifiedByName = "toDto")
    List<FeedbackGeneralDTO> map(List<Feedback> children);
}
