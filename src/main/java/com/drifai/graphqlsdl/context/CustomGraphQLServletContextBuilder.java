package com.drifai.graphqlsdl.context;

import com.drifai.graphqlsdl.context.dataloader.DataLoaderRegistryFactory;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;

@Component
public class CustomGraphQLServletContextBuilder implements GraphQLServletContextBuilder {

    private final DataLoaderRegistryFactory dataLoaderRegistryFactory;

    public CustomGraphQLServletContextBuilder(DataLoaderRegistryFactory dataLoaderRegistryFactory) {
        this.dataLoaderRegistryFactory = dataLoaderRegistryFactory;
    }

    @Override
    public GraphQLContext build(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        // when executing query, mutation from the client
        String userId = httpServletRequest.getHeader("user-id");
        DefaultGraphQLServletContext servletContext = DefaultGraphQLServletContext.createServletContext()
                .with(httpServletRequest)
                .with(httpServletResponse)
                .with(dataLoaderRegistryFactory.build())
                .build();

        return new CustomGraphQLContext(userId, servletContext);
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        // when a subscription is created
        return DefaultGraphQLWebSocketContext.createWebSocketContext()
                .with(session)
                .with(handshakeRequest)
                .build();
    }

    @Override
    public GraphQLContext build() {
        throw new IllegalStateException("Unsupported");
    }
}
