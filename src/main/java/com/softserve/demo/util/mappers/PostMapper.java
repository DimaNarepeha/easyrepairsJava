package com.softserve.demo.util.mappers;

import com.softserve.demo.dto.PostDTO;
import com.softserve.demo.model.Post;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "portfolioId", source = "portfolio.id")
    @Named("toDto")
    PostDTO postToPostDTO(Post post);

    @Mapping(target = "portfolio.id", source = "portfolioId")
    Post postDTOToPost(PostDTO postDTO);

    @IterableMapping(qualifiedByName = "toDto")
    List<PostDTO> map(List<Post> posts);
}