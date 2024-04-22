package org.learning.blogapplication.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.learning.blogapplication.entities.ApiResponse;
import org.learning.blogapplication.entities.dto.CommentDto;
import org.learning.blogapplication.services.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/user/{userId}/post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable Integer userId,@PathVariable Integer postId)
    {
        CommentDto commentDto1 = commentService.createComment(commentDto,userId,postId);
        return new ResponseEntity<CommentDto>(commentDto1, HttpStatus.CREATED);
    }

    @DeleteMapping("comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)
    {
        commentService.deleteComment(commentId);
        return  new ResponseEntity<>(new ApiResponse("Comment Deleted Successfully","success"),HttpStatus.ACCEPTED);
    }
}
