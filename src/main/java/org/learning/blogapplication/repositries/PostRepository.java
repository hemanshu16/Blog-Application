package org.learning.blogapplication.repositries;

import org.learning.blogapplication.entities.models.Category;
import org.learning.blogapplication.entities.models.Post;
import org.learning.blogapplication.entities.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContainingIgnoreCase(String title);
}
