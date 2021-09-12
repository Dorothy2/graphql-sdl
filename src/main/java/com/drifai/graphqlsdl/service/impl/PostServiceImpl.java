package com.drifai.graphqlsdl.service.impl;

import com.drifai.graphqlsdl.dto.PostDto;
import com.drifai.graphqlsdl.exception.ResourceNotFoundException;
import com.drifai.graphqlsdl.mapper.PostMapper;
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
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, AuthorRepository authorRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
        this.postMapper = postMapper;
    }

    @Override
    public List<PostDto> getAllPostByAuthorId(UUID authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);

        Author author =authorOptional.orElseThrow(() -> new ResourceNotFoundException("Author does not exist"));

        List<Post> allByAuthor_id = postRepository.findAllByAuthor_Id(author.getId());
        return allByAuthor_id.stream()
                .map(postMapper::convertPostToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getRecentPosts(int count, int offset) {
        PageRequest pageRequest = PageRequest.of(offset, count);
        Page<Post> all = postRepository.findAll(pageRequest);
        return all.stream()
                .map(postMapper::convertPostToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UUID createPost(PostDto postDto) {

        Optional<Author> authorOptional = authorRepository.findById(postDto.getAuthorId());
        Author author =authorOptional.orElseThrow(() -> new ResourceNotFoundException("Author does not exist"));
        Post post = postMapper.convertDtoToPost(postDto, author);

        Post createdPost = postRepository.saveAndFlush(post);
        return createdPost.getId();
    }

    @Override
    public Integer getPostCountByAuthorId(UUID id) {
        return postRepository.findAllByAuthor_Id(id).size();
    }

    @Override
    public PostDto getPostById(UUID postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        Post p =postOptional.orElseThrow(() -> new ResourceNotFoundException("Post does not exist"));

        return postMapper.convertPostToDto(p);
    }

}
