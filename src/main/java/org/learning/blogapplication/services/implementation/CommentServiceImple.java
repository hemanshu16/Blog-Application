package org.learning.blogapplication.services.implementation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.learning.blogapplication.entities.dto.CommentDto;
import org.learning.blogapplication.entities.models.Comment;
import org.learning.blogapplication.entities.models.Post;
import org.learning.blogapplication.entities.models.User;
import org.learning.blogapplication.exceptions.ResourceNotFound;
import org.learning.blogapplication.repositries.CommentRepository;
import org.learning.blogapplication.repositries.PostRepository;
import org.learning.blogapplication.repositries.UserRepository;
import org.learning.blogapplication.services.interfaces.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@Service
public class CommentServiceImple implements CommentService {

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFound("user","Id",userId));
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFound("Post","Id",postId));
        Comment comment = modelMapper.map(commentDto,Comment.class);
        comment.setUser(user);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment= commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFound("Comment","Id",commentId));
        commentRepository.delete(comment);
    }
}
