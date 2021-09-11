package com.drifai.graphqlsdl.resolver.comment;

import com.drifai.graphqlsdl.dto.AuthorDto;
import com.drifai.graphqlsdl.dto.CommentDto;
import com.drifai.graphqlsdl.dto.PostDto;
import com.drifai.graphqlsdl.service.AuthorService;
import com.drifai.graphqlsdl.service.PostService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class CommentFieldResolver implements GraphQLResolver<CommentDto> {
    private AuthorService authorService;
    private PostService postService;

    public CommentFieldResolver(AuthorService authorService, PostService postService) {
        this.authorService = authorService;
        this.postService = postService;
    }

    public PostDto getPost(CommentDto commentDto) {
        return postService.getPostById(commentDto.getPostId());
    }

    public AuthorDto getAuthor(CommentDto commentDto) {
        return authorService.getAuthorById(commentDto.getAuthorId());
    }
}
