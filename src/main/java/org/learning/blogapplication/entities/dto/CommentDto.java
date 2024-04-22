package org.learning.blogapplication.entities.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private Integer commentId;
    private String content;
    private UserDto user;
}
