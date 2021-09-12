package com.drifai.graphqlsdl.exception;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

public class ResourceNotFoundException extends RuntimeException implements GraphQLError {

    private String message;

    public ResourceNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }

    public ResourceNotFoundException() {

    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public List<Object> getPath() {
        return null;
    }

    //@Override
    //public Map<String, Object> toSpecification() {
     //  return super.
    //}

    @Override
    public Map<String, Object> getExtensions() {
        return null;
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return null;
    }
}
