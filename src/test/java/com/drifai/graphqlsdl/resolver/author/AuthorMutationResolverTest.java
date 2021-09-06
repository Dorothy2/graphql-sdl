package com.drifai.graphqlsdl.resolver.author;

import com.drifai.graphqlsdl.FileReaderUtil;
import com.drifai.graphqlsdl.TestApplication;
import com.drifai.graphqlsdl.dto.AuthorDto;
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
public class AuthorMutationResolverTest {

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    AuthorService authorServiceMock;

    static UUID uuid = UUID.randomUUID();

    @Test
    public void shouldCreateAuthor() throws IOException, JSONException {
        AuthorDto author = AuthorDto.builder()
                .email("test@gmail.com")
                .name("test")
                .build();
        doReturn(uuid).when(authorServiceMock).createAuthor(author);
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource("request/create-author-mutation.graphqls");
        assertEquals(graphQLResponse.isOk(), true);
        //assertTrue(graphQLResponse.get("$.data.createAuthor") != null);
        String uuid = graphQLResponse.get("$.data.createAuthor");
        //assertTrue(uuid != null);
     }

}