package com.drifai.graphqlsdl.resolver.message;

import com.drifai.graphqlsdl.dto.MessageDto;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Configuration
public class MessageMutationResolver implements GraphQLMutationResolver {

    public UUID createMessage(UUID id, String title, Integer[] luckyNumbers, Integer value) {
        return UUID.randomUUID();
    }
}
