package com.drifai.graphqlsdl.resolver.author;

import com.drifai.graphqlsdl.FileReaderUtil;
import com.drifai.graphqlsdl.TestApplication;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
//@GraphQLTest
class AuthorQueryResolverTest {

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    public void shouldGetAuthorData() throws IOException, JSONException {
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource("request/author-query.graphqls");
        assertEquals(graphQLResponse.isOk(), true);
        assertEquals(FileReaderUtil.removeWhiteSpace(FileReaderUtil.read("response/author-query.json")),FileReaderUtil.removeWhiteSpace(graphQLResponse.getRawResponse().getBody()));
    }

    @Test
    public void showReturnPostCountForAuthor() throws IOException {
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource("request/post-count-query.graphqls");
        assertEquals(graphQLResponse.isOk(), true);
        assertEquals(FileReaderUtil.removeWhiteSpace(FileReaderUtil.read("response/post-count-query.json")),FileReaderUtil.removeWhiteSpace(graphQLResponse.getRawResponse().getBody()));
    }

}