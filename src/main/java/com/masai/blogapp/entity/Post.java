package com.masai.blogapp.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name="posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {
	
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY )
	private Integer postID;
	
	@NotNull
	private String title;
	
	@NotNull
	private String description;
	
	@NotNull
	private String content;

	@NotNull
	@Column( name="upload-date")
	private LocalDateTime uploadDate;
	
	@Column( name="updated-date")
	private LocalDateTime updateDate;
	

}
