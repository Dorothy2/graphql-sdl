package com.drifai.graphqlsdl.resolver.post;

import com.drifai.graphqlsdl.dto.PostDto;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
public class PostPublisher {

    private final Sinks.Many<PostDto> processor;

    public PostPublisher() {
        this.processor = Sinks.many().replay().all();
    }

    public Publisher<PostDto> getRecentPost() {
        return processor.asFlux();
    }

    public void publish(PostDto postDto) {
        processor.emitNext(postDto, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
