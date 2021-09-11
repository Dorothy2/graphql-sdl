package com.drifai.graphqlsdl.resolver.post;

import graphql.kickstart.tools.GraphQLMutationResolver;
;
import com.drifai.graphqlsdl.dto.PostDto;
import com.drifai.graphqlsdl.service.PostService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostMutationResolver implements GraphQLMutationResolver {
    private final PostService postService;
    private final PostPublisher postPublisher;

    public PostMutationResolver(PostService postService, PostPublisher postPublisher) {
        this.postService = postService;
        this.postPublisher = postPublisher;
    }

    public UUID createPost(PostDto postDto) {
        UUID uuid = postService.createPost(postDto);
        postDto.setId(uuid);
        postPublisher.publish(postDto);
        return uuid;
    }
}
