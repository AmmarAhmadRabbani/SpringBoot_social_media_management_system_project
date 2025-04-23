package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.PostCommentDto;

public interface PostCommentService {
	public PostCommentDto addPostComment(PostCommentDto postCommentDto);

	public List<PostCommentDto> getPostComment(Long postId);

	public PostCommentDto updatePostComment(PostCommentDto postCommentDto);

	public PostCommentDto deletePostComment(Long postCommentId);

}
