package com.drifai.graphqlsdl.service;

import com.drifai.graphqlsdl.dto.AuthorDto;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

public interface AuthorService {
    List<AuthorDto> getAuthors();

    AuthorDto getAuthorById(UUID authorId);

    UUID createAuthor(AuthorDto authorDto);

    Map<UUID, AuthorDto> getAllAuthorsByIds(Set<UUID> authorIds);
}
