package org.learning.blogapplication.entities.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Integer postId;
    @Size(min = 5, message = "Title length atleast 5 characters")
    private String title;
    @Size(min = 1, message = "Content Should Not Empty")
    private String content;
    @NotNull
    private String imageName;
    private Date addedDate;
    private UserDto user;
    private CategoryDto category;
    private List<CommentDto> comments;
}
