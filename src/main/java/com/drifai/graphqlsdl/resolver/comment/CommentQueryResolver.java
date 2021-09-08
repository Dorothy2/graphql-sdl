package com.drifai.graphqlsdl.resolver.comment;

import com.drifai.graphqlsdl.dto.CommentDto;
import com.drifai.graphqlsdl.service.CommentService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentQueryResolver implements GraphQLQueryResolver {
    private final CommentService commentService;

    public CommentQueryResolver(CommentService commentService) {
        this.commentService = commentService;
    }

    public List<CommentDto> getComments(Integer count, Integer offset) {
        return commentService.getComments(count, offset);
    }
}
