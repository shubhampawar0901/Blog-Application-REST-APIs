package com.masai.blogapp.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.masai.blogapp.DTO.PostDTO;
import com.masai.blogapp.DTO.PostResponse;
import com.masai.blogapp.entity.Post;
import com.masai.blogapp.exception.ResourceNotFoundException;
import com.masai.blogapp.repository.PostRepository;
import com.masai.blogapp.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public PostDTO createPost(PostDTO postDTO) {
		
		Post post = mapToEntity(postDTO);
		post.setUpdateDate(LocalDateTime.now());
		Post newPost = postRepository.save(post);
		
		PostDTO newPostDTO = mapToDTO(newPost);
		return newPostDTO;
		
	}
	
	@Override
	public PostResponse getAllPosts(Integer pageSize, Integer pageNo, String sortBy, String sortMethod) {
		
		Sort sort = sortMethod.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending()
													  :Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		Page<Post> posts = postRepository.findAll(pageable);
		
		List<Post> listPosts = posts.getContent();
		
		List<PostDTO> content =  listPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		
		return postResponse;
	
	}


	@Override
	public PostDTO getPostByID(Long postID) {
		
		Post post = postRepository.findById(postID)
		 			   			   .orElseThrow(()->new ResourceNotFoundException("Post", "postID", postID));
		return mapToDTO(post);
	}
	
	@Override
	public PostDTO updatePost(PostDTO postDTO,  Long postID) {
		
		
		
		Post post = postRepository.findById(postID)
									.orElseThrow(()-> new ResourceNotFoundException("Post", "postID", postID));
		
		if(postDTO.getTitle()!=null)
			post.setTitle(postDTO.getTitle());
		
		if(postDTO.getDescription()!=null)
			post.setDescription(postDTO.getDescription());
		
		if(postDTO.getContent()!=null)
			post.setContent(postDTO.getContent());
		
		post.setUpdateDate(LocalDateTime.now());
		
		Post newPost = postRepository.save(post);
		
		PostDTO newPostDTO = mapToDTO(newPost);
		return newPostDTO;
		
	}
	
	public void deletePost(Long postID) {
		Post post = postRepository.findById(postID)
								  .orElseThrow(()-> new ResourceNotFoundException("Post", "postID", postID));
		
		postRepository.delete(post);
	}
	
	public List<PostDTO> getPostsByTitle(String title){
		
		List<Post> posts = postRepository.findByTitleContainingIgnoreCase(title);
		
		if(posts.size()==0)
			throw new ResourceNotFoundException("Post", "title",title);
		
		return posts.stream().map(post -> mapToDTO(post))
							.collect(Collectors.toList());
	}
	
	public List<PostDTO> getPostsByDate(Integer year, Integer month, Integer date){
		
		LocalDateTime startTime = LocalDateTime.of(year, month, date, 00, 01);
		LocalDateTime endTime 	= LocalDateTime.of(year, month, date, 23, 59);
		
		List<Post> posts = postRepository.findByUploadDateBetween(startTime, endTime);
		
		return posts.stream().map(post -> mapToDTO(post))
							 .collect(Collectors.toList());
		
	}
	
	private Post mapToEntity(PostDTO postDTO) {
		
		Post post = new Post();
		post.setPostID(postDTO.getPostID());
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setContent(postDTO.getContent());
		post.setUploadDate(LocalDateTime.now());
		post.setUpdateDate(postDTO.getUpdateDate());
		
		return post;
	}
	
	private PostDTO mapToDTO(Post post) {
		
		PostDTO postDTO = new PostDTO();
		postDTO.setPostID(post.getPostID());
		postDTO.setTitle(post.getTitle());
		postDTO.setDescription(post.getDescription());
		postDTO.setContent(post.getContent());
		postDTO.setUploadDate(post.getUploadDate());
		postDTO.setUpdateDate(post.getUpdateDate());
		
		return postDTO;
	}


}
