package org.learning.blogapplication.services.interfaces;

import org.learning.blogapplication.entities.dto.PostDto;
import org.learning.blogapplication.entities.dto.PostResponse;
import org.learning.blogapplication.enums.PostField;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostDto getPostById(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String field, String sortDirection);

    List<PostDto> findAllPostByUserId(Integer userId);

    List<PostDto> findAllPostByCategoryId(Integer categoryId);

    List<PostDto> findPostByKeyWord(String keyWord);

}
