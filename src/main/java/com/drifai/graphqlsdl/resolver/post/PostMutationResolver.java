package com.drifai.graphqlsdl.resolver.post;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.drifai.graphqlsdl.dto.PostDto;
import com.drifai.graphqlsdl.service.PostService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostMutationResolver implements GraphQLMutationResolver {
    private final PostService postService;

    public PostMutationResolver(PostService postService) {
        this.postService = postService;
    }

    public UUID createPost(PostDto postDto) {
        return postService.createPost(postDto);
    }
}
