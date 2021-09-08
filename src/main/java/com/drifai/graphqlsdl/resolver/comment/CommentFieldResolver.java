package com.drifai.graphqlsdl.resolver.comment;

import com.drifai.graphqlsdl.dto.CommentDto;
import com.drifai.graphqlsdl.dto.PostDto;
import com.drifai.graphqlsdl.repository.PostRepository;
import com.drifai.graphqlsdl.service.PostService;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class CommentFieldResolver implements GraphQLResolver<CommentDto> {
    private PostService postService;

    public CommentFieldResolver(PostService postService) {
        this.postService = postService;
    }

    public PostDto getPost(CommentDto commentDto) {
        return postService.getPostById(commentDto.getPostId());
    }
}
