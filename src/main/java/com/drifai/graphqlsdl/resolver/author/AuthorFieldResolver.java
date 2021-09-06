package com.drifai.graphqlsdl.resolver.author;

import com.drifai.graphqlsdl.dto.CommentDto;
import com.drifai.graphqlsdl.service.CommentService;
import graphql.kickstart.tools.GraphQLResolver;
import com.drifai.graphqlsdl.dto.AuthorDto;
import com.drifai.graphqlsdl.dto.PostDto;
import com.drifai.graphqlsdl.service.PostService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class AuthorFieldResolver implements GraphQLResolver<AuthorDto> {

    private PostService postService;
    private CommentService commentService;

    public AuthorFieldResolver(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    public List<PostDto> posts(AuthorDto authorDto) {
       return postService.getAllPostByAuthorId(authorDto.getId());
    }

    public Integer postCount(AuthorDto authorDto) {
        return postService.getPostCountByAuthorId(authorDto.getId());
    }

    public List<CommentDto> getComments(AuthorDto authorDto, Integer first) {
        return commentService.getFirstNCommentByAuthorId(authorDto.getId(), first);
    }
}
