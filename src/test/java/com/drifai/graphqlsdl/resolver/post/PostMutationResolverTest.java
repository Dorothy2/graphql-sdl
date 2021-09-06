package com.drifai.graphqlsdl.resolver.post;

import com.drifai.graphqlsdl.TestApplication;
import com.drifai.graphqlsdl.dto.AuthorDto;
import com.drifai.graphqlsdl.dto.PostDto;
import com.drifai.graphqlsdl.service.AuthorService;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
public class PostMutationResolverTest {

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    AuthorService authorServiceMock;

    static UUID uuid = UUID.randomUUID();

    @Test
    public void shouldCreatePost() throws IOException, JSONException {
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource("request/create-post-mutation.graphqls");
        assertEquals(graphQLResponse.isOk(), true);
        String uuid = graphQLResponse.get("$.data.createPost");
        assertTrue(uuid != null);
     }

}