package com.masai.blogapp.service;

import java.util.List;

import com.masai.blogapp.DTO.PostDTO;
import com.masai.blogapp.DTO.PostResponse;
import com.masai.blogapp.exception.ResourceNotFoundException;


public interface PostService {
	
	PostDTO createPost(PostDTO postDTO);
	
	PostResponse getAllPosts(Integer pageSize, Integer pageNo, String sortBy, String sortMethod);
	
	PostDTO getPostByID(Long postID);
	
	PostDTO updatePost(PostDTO postDTO, Long postID);
	
	void deletePost(Long postID);
	
	List<PostDTO> getPostsByTitle(String title);
	
	List<PostDTO> getPostsByDate(Integer year, Integer month, Integer date);

}
