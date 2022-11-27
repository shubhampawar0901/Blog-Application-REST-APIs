package com.masai.blogapp.DTO;

import lombok.Data;

@Data
public class PostDTO {
	
	private Long postID;
	private String title;
	private String description;
	private String content;

}
