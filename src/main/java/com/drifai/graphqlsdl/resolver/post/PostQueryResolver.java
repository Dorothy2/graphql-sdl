package com.drifai.graphqlsdl.resolver.post;

import com.drifai.graphqlsdl.context.CustomGraphQLContext;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.GraphQLResolver;;
import com.drifai.graphqlsdl.dto.PostDto;
import com.drifai.graphqlsdl.service.PostService;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.SelectedField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PostQueryResolver implements GraphQLQueryResolver {

    private final PostService postService;
    private Object SelectedFields;

    public PostQueryResolver(PostService postService) {
        this.postService = postService;
    }

    public List<PostDto> recentPosts(int count, int offset, DataFetchingEnvironment environment) {
        CustomGraphQLContext context = (CustomGraphQLContext) environment.getContext();
        log.info("User id: {}", context.getUserId());
        Set<String> fields = environment.getSelectionSet().getFields()
                        .stream()
                                .map(SelectedField::getName)
                                        .collect(Collectors.toSet());
        log.info("{} Recent posts started, request Fields {}",
                environment.getExecutionId(), fields);
        List<PostDto> list = postService.getRecentPosts(count, offset);
        log.info("{} request completed", environment.getExecutionId());
        return list;
    }

}
