package com.drifai.graphqlsdl.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.drifai.graphqlsdl.dto.AuthorDto;
import com.drifai.graphqlsdl.dto.PostDto;
import com.drifai.graphqlsdl.service.AuthorService;
import com.drifai.graphqlsdl.service.PostService;
import org.springframework.stereotype.Component;

@Component
public class PostFieldResolver implements GraphQLResolver<PostDto> {

    private final AuthorService authorService;

    public PostFieldResolver(AuthorService authorService) {
        this.authorService = authorService;
    }

    public AuthorDto getAuthor(PostDto postDto) {
        return authorService.getAuthorById(postDto.getAuthorId());
     }


}
