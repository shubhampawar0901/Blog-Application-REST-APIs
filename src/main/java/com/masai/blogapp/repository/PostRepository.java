package com.masai.blogapp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.blogapp.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByTitleContainingIgnoreCase(String title);
	
	List<Post> findByUploadDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
