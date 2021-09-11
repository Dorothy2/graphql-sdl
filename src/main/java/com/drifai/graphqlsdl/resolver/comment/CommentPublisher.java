package com.drifai.graphqlsdl.resolver.comment;

import com.drifai.graphqlsdl.dto.CommentDto;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Component
public class CommentPublisher {

    private final Sinks.Many<CommentDto> processor;

    public CommentPublisher() {
        this.processor = Sinks.many().replay().all();
    }

    public Publisher<CommentDto> getRecentComment() {
        return processor.asFlux();
    }

    public void publish(CommentDto commentDto) {
        processor.emitNext(commentDto, Sinks.EmitFailureHandler.FAIL_FAST);
    }

    public Publisher<CommentDto> getRecentCommentByPost(UUID postId) {
        return processor.asFlux().filter(commentDto -> postId.equals(commentDto.getPostId()));
    }
}
