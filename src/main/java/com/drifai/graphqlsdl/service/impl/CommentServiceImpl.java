package com.drifai.graphqlsdl.service.impl;

import com.drifai.graphqlsdl.dto.CommentDto;
import com.drifai.graphqlsdl.exception.ResourceNotFoundException;
import com.drifai.graphqlsdl.mapper.CommentMapper;
import com.drifai.graphqlsdl.model.Author;
import com.drifai.graphqlsdl.model.Comment;
import com.drifai.graphqlsdl.model.Post;
import com.drifai.graphqlsdl.repository.AuthorRepository;
import com.drifai.graphqlsdl.repository.CommentRepository;
import com.drifai.graphqlsdl.repository.PostRepository;
import com.drifai.graphqlsdl.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, AuthorRepository authorRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public List<CommentDto> getComments(Integer count, Integer offset) {
        Page<Comment> all = commentRepository.findAll(PageRequest.of(offset, count));

        return all.stream()
                .map(commentMapper::convertCommentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getFirstNCommentByAuthorId(UUID authorId, Integer count) {
        List<Comment> allByAuthorId = commentRepository.findAllByAuthor_Id(authorId, PageRequest.of(0, count));

        return allByAuthorId.stream()
                .map(commentMapper::convertCommentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getFirstNCommentByPostId(UUID postId, Integer count) {
        List<Comment> allByPostId = commentRepository.findAllByPost_Id(postId, PageRequest.of(0, count));

        return allByPostId.stream()
                .map(commentMapper::convertCommentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UUID createComment(CommentDto commentDto) {
        Optional<Post> postOptional = postRepository.findById(commentDto.getPostId());
        Post post = postOptional.orElseThrow(() -> new ResourceNotFoundException("Post does not exist"));

        Optional<Author> authorOptional = authorRepository.findById(commentDto.getAuthorId());
        Author author = authorOptional.orElseThrow(() -> new ResourceNotFoundException("Author does not exist"));

        Comment comment = commentMapper.convertDtoToComment(commentDto, author, post);

        Comment createdComment = commentRepository.saveAndFlush(comment);
        return createdComment.getId();
    }
}

