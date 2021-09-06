package com.drifai.graphqlsdl.service.impl;

import com.drifai.graphqlsdl.dto.PostDto;
import com.drifai.graphqlsdl.model.Author;
import com.drifai.graphqlsdl.model.Post;
import com.drifai.graphqlsdl.repository.AuthorRepository;
import com.drifai.graphqlsdl.repository.PostRepository;
import com.drifai.graphqlsdl.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final AuthorRepository authorRepository;

    public PostServiceImpl(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<PostDto> getAllPostByAuthorId(UUID authorId) {
        List<Post> allByAuthor_id = postRepository.findAllByAuthor_Id(authorId);
        return allByAuthor_id.stream()
                .map(post -> {
                    return PostDto.builder()
                     .id(post.getId())
                     .authorId(authorId)
                     .description(post.getDescription())
                     .title(post.getTitle())
                     .category(post.getCategory())
                     .build();
                }).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getRecentPosts(int count, int offset) {
        PageRequest pageRequest = PageRequest.of(offset, count);
        Page<Post> all = postRepository.findAll(pageRequest);
        return all.stream()
                .map(post -> {
                    return PostDto.builder()
                            .id(post.getId())
                            .authorId(post.getAuthor().getId())
                            .description(post.getDescription())
                            .title(post.getTitle())
                            .category(post.getCategory())
                            .build();
                }).collect(Collectors.toList());
    }

    @Override
    public UUID createPost(PostDto postDto) {

        Optional<Author> author = authorRepository.findById(postDto.getAuthorId());
        if(!author.isPresent()) {
            throw new RuntimeException("Author does not exist.");
        }
        Post post = Post.builder()
            .title(postDto.getTitle())
            .description(postDto.getDescription())
            .category(postDto.getCategory())
            .author(author.get())
            .build();

        Post createdPost = postRepository.saveAndFlush(post);
        return createdPost.getId();
    }

    @Override
    public Integer getPostCountByAuthorId(UUID id) {
        return postRepository.findAllByAuthor_Id(id).size();
    }
}
