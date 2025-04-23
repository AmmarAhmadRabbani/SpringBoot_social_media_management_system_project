package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.PostDto;

public interface PostService {
	public PostDto addPost(PostDto postDto);

	public List<PostDto> getPost(Long userId);

	public PostDto updatePostDto(PostDto postDto);

	public PostDto deletePostDto(Long postId);

}
