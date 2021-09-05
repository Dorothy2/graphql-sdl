package com.drifai.graphqlsdl.resolver.author;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.drifai.graphqlsdl.dto.AuthorDto;
import com.drifai.graphqlsdl.dto.PostDto;
import com.drifai.graphqlsdl.service.PostService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class AuthorFieldResolver implements GraphQLResolver<AuthorDto> {

    private PostService postService;

    public AuthorFieldResolver(PostService postService) {
        this.postService = postService;
    }

    public List<PostDto> posts(AuthorDto authorDto) {
       return postService.getAllPostByAuthorId(authorDto.getId());
    }
}
