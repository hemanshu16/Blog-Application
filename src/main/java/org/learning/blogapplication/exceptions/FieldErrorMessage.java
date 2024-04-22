package org.learning.blogapplication.exceptions;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FieldErrorMessage {

    private String name;
    private String message;
}
