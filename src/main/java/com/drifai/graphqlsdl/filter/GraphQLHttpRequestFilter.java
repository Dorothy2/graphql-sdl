package com.drifai.graphqlsdl.filter;

import graphql.kickstart.servlet.core.GraphQLServletListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
public class GraphQLHttpRequestFilter  implements GraphQLServletListener {

    private final Clock clock = Clock.systemDefaultZone();

    @Override
    public RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {
        return new RequestCallback() {

            final Instant startTime = Instant.now();

            @Override
            public void onSuccess(HttpServletRequest request, HttpServletResponse response) {
                RequestCallback.super.onSuccess(request, response);
            }

            @Override
            public void onError(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {
                RequestCallback.super.onError(request, response, throwable);
            }

            @Override
            public void onFinally(HttpServletRequest request, HttpServletResponse response) {
                RequestCallback.super.onFinally(request, response);
                log.info("Request completed in {}", Duration.between(startTime, Instant.now()));

            }
        };
    }
}
