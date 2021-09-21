package com.drifai.graphqlsdl.resolver.post;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.GraphQLResolver;;
import com.drifai.graphqlsdl.dto.PostDto;
import com.drifai.graphqlsdl.service.PostService;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class PostQueryResolver implements GraphQLQueryResolver {

    private final PostService postService;

    public PostQueryResolver(PostService postService) {
        this.postService = postService;
    }

    public List<PostDto> recentPosts(int count, int offset, DataFetchingEnvironment environment) {
        log.info("{} Recent posts started", environment.getExecutionId());
        return postService.getRecentPosts(count, offset);
    }

}
