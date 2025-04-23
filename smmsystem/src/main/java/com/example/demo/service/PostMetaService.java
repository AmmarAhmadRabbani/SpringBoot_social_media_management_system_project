package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.PostMetaDto;

public interface PostMetaService {
	public PostMetaDto addPostMeta(PostMetaDto postMetaDto);

	public List<PostMetaDto> getPostMeta(Long postId);

	public PostMetaDto updatePostMetaDto(PostMetaDto postMetaDto);

	public PostMetaDto deletePostMetaDto(Long postMetaId);

}
