package com.drifai.graphqlsdl.resolver.post;

import com.drifai.graphqlsdl.dto.CommentDto;
import com.drifai.graphqlsdl.service.CommentService;
import graphql.kickstart.tools.GraphQLResolver;;
import com.drifai.graphqlsdl.dto.AuthorDto;
import com.drifai.graphqlsdl.dto.PostDto;
import com.drifai.graphqlsdl.service.AuthorService;
import com.drifai.graphqlsdl.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class PostFieldResolver implements GraphQLResolver<PostDto> {

    private final AuthorService authorService;
    private final CommentService commentService;

    private final ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public PostFieldResolver(AuthorService authorService, CommentService commentService) {
        this.authorService = authorService;
        this.commentService = commentService;
    }

    public CompletableFuture<AuthorDto> getAuthor(PostDto postDto) {
        log.info("Author main thread id: {}", Thread.currentThread().getId());
        return CompletableFuture.supplyAsync(() -> {
          log.info("Author thread id: {}", Thread.currentThread().getId());
          log.info("Author request started for postId:{}.", postDto.getId());
          AuthorDto authorById = authorService.getAuthorById(postDto.getAuthorId());
          log.info("Author request completed for postId:{}.", postDto.getId());
          return authorById;
        }, executorService);
     }

    public CompletableFuture<List<CommentDto>> getComments(PostDto postDto, Integer first) {
        log.info("Comment main thread id: {}", Thread.currentThread().getId());
        return CompletableFuture.supplyAsync(() -> {
          log.info("Comment thread id: {}", Thread.currentThread().getId());
          log.info("Comment request started for postId:{}.", postDto.getId());
          List<CommentDto> list = commentService.getFirstNCommentByPostId(postDto.getId(), first);
          log.info("Comment request ended for postId:{}.", postDto.getId());
          return list;
        }, executorService);
    }
}
