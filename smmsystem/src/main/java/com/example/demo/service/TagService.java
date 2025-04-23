package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.TagDto;

public interface TagService {
	public TagDto addTag(TagDto tagDto);

	public List<TagDto> getTag(Long postId);

	public TagDto updateTag(TagDto tagDto);

	public TagDto deleteTag(Long tagId);

}
