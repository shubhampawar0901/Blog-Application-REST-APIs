package com.masai.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.masai.blogapp.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
