package com.drifai.graphqlsdl.resolver.post;

import com.drifai.graphqlsdl.FileReaderUtil;
import com.drifai.graphqlsdl.TestApplication;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
class PostQueryResolverTest {

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    public void shouldGetPostData() throws IOException, JSONException {
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource("request/recent-post-query.graphqls");
        assertEquals(graphQLResponse.isOk(), true);
        assertEquals(FileReaderUtil.removeWhiteSpace(FileReaderUtil.read("response/recent-post-query.json")),FileReaderUtil.removeWhiteSpace(graphQLResponse.getRawResponse().getBody()));
    }
}