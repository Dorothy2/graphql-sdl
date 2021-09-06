package com.drifai.graphqlsdl.service.impl;

import com.drifai.graphqlsdl.dto.CommentDto;
import com.drifai.graphqlsdl.model.Comment;
import com.drifai.graphqlsdl.repository.CommentRepository;
import com.drifai.graphqlsdl.service.CommentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentDto> getFirstNCommentByAuthorId(UUID authorId, Integer count) {
        List<Comment> allByAuthorId =  commentRepository.findAllByAuthor_Id(authorId, PageRequest.of(0, count));

        return allByAuthorId.stream()
          .map(comment -> CommentDto.builder()
              .id(comment.getId())
              .text(comment.getText())
              .authorId(comment.getAuthor().getId())
              .build())
          .collect(Collectors.toList());
    }
}
