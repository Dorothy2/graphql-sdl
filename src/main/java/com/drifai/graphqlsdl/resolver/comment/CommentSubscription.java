package com.drifai.graphqlsdl.resolver.comment;

import com.drifai.graphqlsdl.dto.CommentDto;
import com.drifai.graphqlsdl.dto.PostDto;
import com.drifai.graphqlsdl.resolver.post.PostPublisher;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentSubscription implements GraphQLSubscriptionResolver {
    private final  CommentPublisher commentPublisher;

    public CommentSubscription(CommentPublisher commentPublisher) {
        this.commentPublisher = commentPublisher;
    }

    public Publisher<CommentDto> recentComment() {
       return commentPublisher.getRecentComment();
    }

    public Publisher<CommentDto> recentCommentByPost(UUID postId) {
        return commentPublisher.getRecentCommentByPost(postId);
    }
}
