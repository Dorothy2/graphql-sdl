package com.drifai.graphqlsdl.resolver.message;

import com.drifai.graphqlsdl.dto.MessageDto;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Configuration
public class MessageMutationResolver implements GraphQLMutationResolver {

    public UUID createMessage(UUID id, String title) {
        return UUID.randomUUID();
        //return MessageDto.builder()
        //        .id(id)
        //        .text("This is a text message")
        //        .build();
    }
}
