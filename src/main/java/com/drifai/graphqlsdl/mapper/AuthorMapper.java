package com.drifai.graphqlsdl.mapper;

import com.drifai.graphqlsdl.dto.AuthorDto;
import com.drifai.graphqlsdl.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorDto convertAuthorToDto(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .email(author.getEmail())
                .name(author.getName())
                .build();
    }

    public Author convertDtoToAuthor(AuthorDto authorDto) {
        return  Author.builder()
                .email(authorDto.getEmail())
                .name(authorDto.getName())
                .build();
    }
}
