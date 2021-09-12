package com.drifai.graphqlsdl.mapper;

import com.drifai.graphqlsdl.dto.CommentDto;
import com.drifai.graphqlsdl.model.Author;
import com.drifai.graphqlsdl.model.Comment;
import com.drifai.graphqlsdl.model.Post;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentDto convertCommentToDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .authorId(comment.getAuthor().getId())
                .postId(comment.getPost().getId())
                .build();
    }

    public Comment convertDtoToComment(CommentDto commentDto, Author author, Post post) {
         return  Comment.builder()
                .text(commentDto.getText())
                .post(post)
                .author(author)
                .build();
    }
}
