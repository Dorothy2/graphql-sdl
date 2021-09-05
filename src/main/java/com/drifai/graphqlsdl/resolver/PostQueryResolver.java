package com.drifai.graphqlsdl.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.drifai.graphqlsdl.dto.PostDto;
import com.drifai.graphqlsdl.service.PostService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class PostQueryResolver implements GraphQLQueryResolver {

    private final PostService postService;

    public PostQueryResolver(PostService postService) {
        this.postService = postService;
    }

    public List<PostDto> recentPosts(int count, int offset) {
       return postService.getRecentPosts(count, offset);
    }

}
