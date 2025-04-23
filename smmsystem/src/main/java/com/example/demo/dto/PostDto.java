package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	private Long postId;
	private Long parentId;
	private String title;
	private Integer published;
	private String metaTile;
	private String slug;
	private String summary;
	private String context;
	private Long userId;

}
