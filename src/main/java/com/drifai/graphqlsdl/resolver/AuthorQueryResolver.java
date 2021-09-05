package com.drifai.graphqlsdl.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.drifai.graphqlsdl.dto.AuthorDto;
import com.drifai.graphqlsdl.service.AuthorService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class AuthorQueryResolver implements GraphQLQueryResolver {

    private final AuthorService authorService;

    public AuthorQueryResolver(AuthorService authorService) {
        this.authorService = authorService;
    }

    public List<AuthorDto> getAuthors() {
         return authorService.getAuthors();
    }
}

