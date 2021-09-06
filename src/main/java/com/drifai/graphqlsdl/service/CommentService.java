package com.drifai.graphqlsdl.service;

import com.drifai.graphqlsdl.dto.CommentDto;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    List<CommentDto> getFirstNCommentByAuthorId(UUID uuid, Integer count);
}
