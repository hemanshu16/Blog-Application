package org.learning.blogapplication.services.implementation;

import org.learning.blogapplication.entities.models.Category;
import org.learning.blogapplication.entities.models.Post;
import org.learning.blogapplication.entities.models.User;
import org.learning.blogapplication.entities.dto.PostDto;
import org.learning.blogapplication.entities.dto.PostResponse;
import org.learning.blogapplication.enums.PostField;
import org.learning.blogapplication.exceptions.ResourceNotFound;
import org.learning.blogapplication.repositries.CategoryRepository;
import org.learning.blogapplication.repositries.PostRepository;
import org.learning.blogapplication.repositries.UserRepository;
import org.learning.blogapplication.services.interfaces.PostService;
import org.learning.blogapplication.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImple implements PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;
    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("user", "id", userId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFound("category", "id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        PostDto postDto1 = modelMapper.map(savedPost, PostDto.class);
        return postDto1;
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "Id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = postRepository.save(post);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "Id", postId));
        postRepository.delete(post);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "Id", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String field, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),PostField.fromString(field).getFieldName());
        Pageable pageable =  PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePosts = postRepository.findAll(pageable);
        List<Post> posts = pagePosts.toList();
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse(postDtos,
                pagePosts.getNumber(),
                pagePosts.getSize(),
                pagePosts.getTotalElements(),
                pagePosts.getTotalPages(),
                pagePosts.isLast()
                );

        return  postResponse;
    }

    @Override
    public List<PostDto> findAllPostByUserId(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("user", " Id ", userId));
        List<Post> posts = postRepository.findByUser(user);
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> findAllPostByCategoryId(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFound("Category", "Id", categoryId));
        List<Post> posts = postRepository.findByCategory(category);
        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> findPostByKeyWord(String keyWord) {
        List<Post> posts = postRepository.findByTitleContainingIgnoreCase(keyWord);
        List<PostDto> postDtos = posts.stream().map((post)->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
