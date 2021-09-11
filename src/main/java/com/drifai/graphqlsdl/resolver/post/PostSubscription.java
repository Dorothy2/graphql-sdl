package com.drifai.graphqlsdl.resolver.post;

import com.drifai.graphqlsdl.dto.PostDto;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PostSubscription implements GraphQLSubscriptionResolver {
    private final PostPublisher postPublisher;

    public PostSubscription(PostPublisher postPublisher) {
        this.postPublisher = postPublisher;
    }

    public Publisher<PostDto> recentPost() {
       return postPublisher.getRecentPost();
    }
}
