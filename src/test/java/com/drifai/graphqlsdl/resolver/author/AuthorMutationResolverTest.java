package com.drifai.graphqlsdl.resolver.author;

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
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
//@GraphQLTest
class AuthorMutationResolverTest {

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    public void shouldCreateAuthor() throws IOException, JSONException {
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource("request/create-author-mutation.graphqls");
        String uuid = graphQLResponse.get("$.data.createAuthor");
        //assertTrue(uuid != null);
     }

}