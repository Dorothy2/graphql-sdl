package com.drifai.graphqlsdl.config;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

@Configuration
public class EmailScalar {

    @Bean
    public GraphQLScalarType email() {
        return new GraphQLScalarType("Email", "custom email scalar type", new Coercing() {

            @Override
            public Object serialize(@NotNull Object dataFetcherResult) throws CoercingSerializeException {
                if(dataFetcherResult instanceof StringValue) {
                    return dataFetcherResult.toString();
                }
                throw new CoercingSerializeException("Email is not valid.");
            }

            @NotNull
            @Override
            public Object parseValue(@NotNull Object input) throws CoercingParseValueException {
                if(input instanceof String) {
                    if(isValid(input.toString())) {
                        return input.toString();
                    }
                }
                throw new CoercingParseValueException("Input email is not valid.");
            }

            @NotNull
            @Override
            public Object parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {
                if(input instanceof StringValue) {
                   if(isValid(input.toString())) {
                       return input.toString();
                   }
                }
                throw new CoercingParseLiteralException("Input email is not valid.");
            }
        } );

    }

    private boolean isValid(String input) {
        if(Pattern.matches(".+\\@.+\\..+",input)) {
            return true;
        }
        return false;
    }
}


