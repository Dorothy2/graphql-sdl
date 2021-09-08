package com.drifai.graphqlsdl.resolver.post;

import com.drifai.graphqlsdl.dto.CommentDto;
import com.drifai.graphqlsdl.service.CommentService;
import graphql.kickstart.tools.GraphQLResolver;;
import com.drifai.graphqlsdl.dto.AuthorDto;
import com.drifai.graphqlsdl.dto.PostDto;
import com.drifai.graphqlsdl.service.AuthorService;
import com.drifai.graphqlsdl.service.PostService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostFieldResolver implements GraphQLResolver<PostDto> {

    private final AuthorService authorService;
    private final PostService postService;
    private final CommentService commentService;

    public PostFieldResolver(AuthorService authorService, PostService postService, CommentService commentService) {
        this.authorService = authorService;
        this.postService = postService;
        this.commentService = commentService;
    }

    public AuthorDto getAuthor(PostDto postDto) {
        return authorService.getAuthorById(postDto.getAuthorId());
     }

    public List<CommentDto> getComments(PostDto postDto, Integer first) {
        return commentService.getFirstNCommentByPostId(postDto.getId(), first);
    }
}
