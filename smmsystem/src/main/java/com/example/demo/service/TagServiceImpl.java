package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TagDto;
import com.example.demo.entity.Post;
import com.example.demo.entity.Tag;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.TagRepository;

@Service
public class TagServiceImpl implements TagService {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private TagRepository tagRepository;

	@Override
	public TagDto addTag(TagDto tagDto) {
		Post post = postRepository.findById(tagDto.getPostId())
				.orElseThrow(() -> new UserNotFoundException("invalid post id"));
		Tag tag = new Tag();
		BeanUtils.copyProperties(tagDto, tag);
		tag.setPostList(List.of(post));
		tagRepository.save(tag);
		return tagDto;
	}

	@Override
	public List<TagDto> getTag(Long postId) {
		Optional<Post> findById = postRepository.findById(postId);
		if (findById.isEmpty()) {
			throw new UserNotFoundException("invalid id");

		}
		Post post = findById.get();
		List<Tag> tagList = post.getTagList();
		List<TagDto> tagDtoList = new ArrayList<>();
		tagList.forEach(i -> {
			TagDto tagDto = new TagDto();
			BeanUtils.copyProperties(i, tagDto);
			tagDtoList.add(tagDto);

		});
		return tagDtoList;
	}

	@Override
	public TagDto updateTag(TagDto tagDto) {
		Optional<Tag> findById = tagRepository.findById(tagDto.getTagId());
		if (findById.isPresent()) {
			BeanUtils.copyProperties(tagDto, findById.get());
			tagRepository.save(findById.get());
			BeanUtils.copyProperties(findById.get(), tagDto);
			return tagDto;

		}
		throw new UserNotFoundException("invalid id");
	}

	@Override
	public TagDto deleteTag(Long tagId) {
		Optional<Tag> findById = tagRepository.findById(tagId);
		if (findById.isPresent()) {
			tagRepository.deleteById(tagId);
			return new TagDto();

		}
		throw new UserNotFoundException("id not present");
	}

}
