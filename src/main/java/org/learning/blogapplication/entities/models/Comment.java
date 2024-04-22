package org.learning.blogapplication.entities.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String content;

    @ManyToOne()
    @JoinColumn(name="userId",referencedColumnName = "id")
    private User user;

    @ManyToOne()
    @JoinColumn(name="postId",referencedColumnName = "postId")
    private Post post;
}
