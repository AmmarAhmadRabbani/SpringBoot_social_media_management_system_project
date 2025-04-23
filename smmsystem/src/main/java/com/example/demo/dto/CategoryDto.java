package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	private int id;
	private String title;
	private String metaTile;
	private Long parentId;
	private String slug;
	private String content;
	private Long postId;

}
