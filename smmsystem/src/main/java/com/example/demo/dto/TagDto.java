package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {
	private Long tagId;
	private String title;
	private String metaTile;
	private String slug;
	private String content;
	private Long postId;

}
