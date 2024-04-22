package org.learning.blogapplication.services.interfaces;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.learning.blogapplication.entities.dto.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId, Integer userId);
    void deleteComment(Integer commentId);

}
