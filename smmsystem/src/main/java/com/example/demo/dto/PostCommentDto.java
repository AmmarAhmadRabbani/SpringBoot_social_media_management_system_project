package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentDto {
	private Long postCommentId;
	private Long parentId;
	private String title;
	private String published;
	private String content;
	private Long postId;
	private Long userId;

}
