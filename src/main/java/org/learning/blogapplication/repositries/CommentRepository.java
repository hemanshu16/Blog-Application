package org.learning.blogapplication.repositries;

import org.learning.blogapplication.entities.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
