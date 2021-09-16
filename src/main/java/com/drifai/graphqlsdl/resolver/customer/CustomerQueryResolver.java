package com.drifai.graphqlsdl.resolver.customer;

import com.drifai.graphqlsdl.dto.CustomerDto;
import com.drifai.graphqlsdl.dto.MessageDto;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Component
public class CustomerQueryResolver implements GraphQLQueryResolver {

    public CustomerDto getCustomer(String phoneNumber) {
       return CustomerDto.builder()
              .birthDate(LocalDate.now())
              .workStartTime(OffsetTime.now())
              .bornAt(OffsetDateTime.now())
              .profileLink("some link")
              .build();
    }
}
