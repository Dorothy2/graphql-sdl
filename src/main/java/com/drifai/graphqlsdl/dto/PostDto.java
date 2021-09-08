package com.drifai.graphqlsdl.dto;

import com.drifai.graphqlsdl.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private UUID id;
    private String title;
    private String category;
    private String description;
    private UUID authorId;
    private Set<Comment> comments;
}
