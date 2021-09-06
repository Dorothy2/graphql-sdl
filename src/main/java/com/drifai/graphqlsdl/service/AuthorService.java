package com.drifai.graphqlsdl.service;

import com.drifai.graphqlsdl.dto.AuthorDto;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    List<AuthorDto> getAuthors();

    AuthorDto getAuthorById(UUID authorId);

    UUID createAuthor(AuthorDto authorDto);
}
