package com.drifai.graphqlsdl.mapper;

import com.drifai.graphqlsdl.dto.PostDto;
import com.drifai.graphqlsdl.model.Author;
import com.drifai.graphqlsdl.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostDto convertPostToDto(Post p) {
        return PostDto.builder()
                .id(p.getId())
                .title(p.getTitle())
                .authorId(p.getAuthor().getId())
                .category(p.getCategory())
                .description(p.getDescription())
                .comments(p.getComments())
                .build();
    }

    public Post convertDtoToPost(PostDto postDto, Author author) {
        return Post.builder()
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .category(postDto.getCategory())
                .author(author)
                .build();
    }
}
