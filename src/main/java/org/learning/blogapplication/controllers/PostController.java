package org.learning.blogapplication.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.learning.blogapplication.config.AppConstant;
import org.learning.blogapplication.entities.ApiResponse;
import org.learning.blogapplication.entities.dto.PostDto;
import org.learning.blogapplication.entities.dto.PostResponse;
import org.learning.blogapplication.services.interfaces.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {

    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> creatPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        PostDto postDto1 = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(postDto1, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable Integer userId) {
        List<PostDto> postDtos = postService.findAllPostByUserId(userId);
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/category/{categoryId}/post")
    public ResponseEntity<List<PostDto>> getAllPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> postDtos = postService.findAllPostByCategoryId(categoryId);
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/post")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NO, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sort", defaultValue = AppConstant.SORT_BY, required = false) String field,
            @RequestParam(value = "direction", defaultValue = AppConstant.ORDER_BY, required = false) String sortDirection) {
        return ResponseEntity.ok(postService.getAllPost(pageNumber, pageSize, field, sortDirection));
    }

    @GetMapping("/post/search/{keyWord}")
    public ResponseEntity<List<PostDto>> getPostsByKeyWord(@PathVariable String keyWord)
    {
        List<PostDto> postDtos = postService.findPostByKeyWord(keyWord);
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully", "Success"), HttpStatus.ACCEPTED);
    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId, @Valid @RequestBody PostDto postDto) {
        PostDto postDto1 = postService.updatePost(postDto, postId);
        return ResponseEntity.ok(postDto1);
    }

}
