package com.drifai.graphqlsdl.resolver.comment;

import com.drifai.graphqlsdl.dto.CommentDto;
import com.drifai.graphqlsdl.resolver.comment.CommentPublisher;
import com.drifai.graphqlsdl.service.CommentService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentMutationResolver implements GraphQLMutationResolver {
    private final CommentService commentService;
    private final CommentPublisher commentPublisher;

    public CommentMutationResolver(CommentService commentService, CommentPublisher commentPublisher) {
        this.commentService = commentService;
        this.commentPublisher = commentPublisher;
    }


    public UUID createComment(CommentDto commentDto) {
        UUID uuid = commentService.createComment(commentDto);
        commentDto.setId(uuid);
        commentPublisher.publish(commentDto);
        return uuid;
    }
}
