package com.masai.blogapp.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostDTO {
	
	private Long postID;
	private String title;
	private String description;
	private String content;
	private LocalDateTime uploadDate;
	private LocalDateTime updateDate;
}
