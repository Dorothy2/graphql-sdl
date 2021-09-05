package com.drifai.graphqlsdl.service;

import com.drifai.graphqlsdl.dto.PostDto;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<PostDto> getAllPostByAuthorId(UUID authorId);

    List<PostDto> getRecentPosts(int count, int offset);

    UUID createPost(PostDto postDto);
}
