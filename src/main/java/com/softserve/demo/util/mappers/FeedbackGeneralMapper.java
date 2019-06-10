package com.softserve.demo.util.mappers;

import com.softserve.demo.dto.FeedbackGeneralDTO;
import com.softserve.demo.model.Feedback;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedbackGeneralMapper {

            @Mapping(target = "id", source = "entity.id")
            @Mapping(target = "comment", source = "entity.comment")
            @Mapping(target = "rating", source = "entity.rating")
            @Mapping(target = "username", source = "entity.addressedFrom.username")
            @Mapping(target = "image", source = "entity.addressedFrom.image")
            @Mapping(target = "createdDate", source = "entity.createdDate")
    @Named("toDto")
    FeedbackGeneralDTO convertToDTO(Feedback entity);

            @Mapping(target = "id", source = "dto.id")
            @Mapping(target = "comment", source = "dto.comment")
            @Mapping(target = "rating", source = "dto.rating")
            @Mapping(target = "createdDate", source = "dto.createdDate")
    Feedback convertToEntity(FeedbackGeneralDTO dto);

    @IterableMapping(qualifiedByName = "toDto")
    List<FeedbackGeneralDTO> map(List<Feedback> children);
}
