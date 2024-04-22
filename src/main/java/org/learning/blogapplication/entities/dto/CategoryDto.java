package org.learning.blogapplication.entities.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer categoryId;
    @NotNull(message = "Title must not be empty")
    @Size(min = 5, message = "Title length at least 5 characters")
    private String title;
    @NotNull(message = "Description must not be empty")
    @Size(min = 10, message = "Description length at least 10 characters")
    private String description;
}
