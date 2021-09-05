package com.drifai.graphqlsdl.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.drifai.graphqlsdl.dto.MessageDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class HelloWorldQueryResolver implements GraphQLQueryResolver {
    public String helloWorld() {
        return "Hello World! graphql is awesome!";
    }

    public String greetingMessage(String firstName, String lastName) {
        StringBuilder sb = new StringBuilder("Hello ").append(firstName);
        if(lastName != null) {
            sb.append(" ").append(lastName);
        }
        return sb.toString();
    }

    public MessageDto getMessage() {
       return MessageDto.builder()
               .id(UUID.randomUUID())
               .text("This is a text message")
               .build();
    }

    public List<Integer> rollDice() {
        return Arrays.asList(1,2,3,4);
    }
}
