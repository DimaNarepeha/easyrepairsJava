package com.softserve.demo.util;

import com.softserve.demo.dto.FeedbackGeneralDTO;
import com.softserve.demo.model.Feedback;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedbackGeneralMapper {
    @Mappings({
            @Mapping(target = "id", source = "entity.id"),
            @Mapping(target = "comment", source = "entity.comment"),
            @Mapping(target = "rating", source = "entity.rating"),
            @Mapping(target = "username", source = "entity.addressedFrom.username")
    })
    @Named("toDto")
    FeedbackGeneralDTO convertToDTO(Feedback entity);

    @Mappings({
            @Mapping(target = "id", source = "dto.id"),
            @Mapping(target = "comment", source = "dto.comment"),
            @Mapping(target = "rating", source = "dto.rating"),

    })
    Feedback convertToEntity(FeedbackGeneralDTO dto);

    @IterableMapping(qualifiedByName = "toDto")
    List<FeedbackGeneralDTO> map(List<Feedback> children);
}
