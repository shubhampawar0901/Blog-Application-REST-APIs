package com.masai.blogapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.blogapp.DTO.PostDTO;
import com.masai.blogapp.DTO.PostResponse;
import com.masai.blogapp.service.impl.PostServiceImpl;
import com.masai.blogapp.utils.PostConstant;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostServiceImpl postServiceImpl;
	
	@PostMapping
	public ResponseEntity<PostDTO> createPostController(@RequestBody PostDTO postDTO ){
		
		return new ResponseEntity<>(postServiceImpl.createPost(postDTO), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<PostResponse> getAllPostsController(
			@RequestParam(value = "pageSize",    defaultValue= PostConstant.DEFAULT_PAGE_SIZE,   required = false) Integer pageSize,
			@RequestParam(value = "pageNo" ,     defaultValue= PostConstant.DEFAULT_PAGE_NO, 	 required = false) Integer pageNo,
			@RequestParam(value = "sortBy" ,     defaultValue= PostConstant.DEFAULT_SORT_METHOD, required = false) String sortBy,
			@RequestParam(value = "sortMethod" , defaultValue= PostConstant.DEFAULT_SORT_DIR,    required = false) String sortMethod){
		
		return new ResponseEntity<PostResponse> (postServiceImpl.getAllPosts(pageSize, pageNo, sortBy, sortMethod), HttpStatus.FOUND);
	}
	
	@GetMapping("/{postID}")
	public ResponseEntity<PostDTO> getPostByIDController(@PathVariable("postID") Long postID){
		
		return new ResponseEntity<PostDTO> (postServiceImpl.getPostByID(postID), HttpStatus.FOUND);
	}
	
	@PutMapping("/{postID}")
	public ResponseEntity<PostDTO> updatePostController(@PathVariable("postID") Long postID,
													    @RequestBody PostDTO postDTO){
		
		return new ResponseEntity<PostDTO> (postServiceImpl.updatePost(postDTO, postID),HttpStatus.OK);
	}
	
	@DeleteMapping("/{postID}")
	public ResponseEntity<String> deletePostController(@PathVariable("postID") Long postID){
		
		postServiceImpl.deletePost(postID);
		return new ResponseEntity<String>("Post with postID : "+ postID +" deleted successfuly!", HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<PostDTO>> getPostsByTitleController(@RequestParam String title){
		
		return new ResponseEntity<>(postServiceImpl.getPostsByTitle(title),HttpStatus.FOUND);
	}
	
	@GetMapping("/getpostof/{year}/{month}/{date}")
	public ResponseEntity<List<PostDTO>> getPostsByDateController(@PathVariable("year") Integer year,
																  @PathVariable("month")Integer month,
																  @PathVariable("date")Integer date){
		
		return new ResponseEntity<>(postServiceImpl.getPostsByDate(year,month, date),HttpStatus.FOUND); 
	}
}
