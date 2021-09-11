package com.drifai.graphqlsdl.service;

import com.drifai.graphqlsdl.dto.CommentDto;
import com.drifai.graphqlsdl.dto.PostDto;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    List<CommentDto> getComments(Integer count, Integer offset);

    List<CommentDto> getFirstNCommentByAuthorId(UUID authorId, Integer count);

    List<CommentDto> getFirstNCommentByPostId(UUID postId, Integer count);

    UUID createComment(CommentDto commentDto);
}
