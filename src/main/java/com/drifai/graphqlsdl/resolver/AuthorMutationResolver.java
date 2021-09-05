package com.drifai.graphqlsdl.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.drifai.graphqlsdl.dto.AuthorDto;
import com.drifai.graphqlsdl.service.AuthorService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthorMutationResolver implements GraphQLMutationResolver {

    private final AuthorService authorService;

    public AuthorMutationResolver(AuthorService authorService) {
        this.authorService = authorService;
    }

    public UUID createAuthor(AuthorDto authorDto) {
        return authorService.createAuthor(authorDto);
    }
}
