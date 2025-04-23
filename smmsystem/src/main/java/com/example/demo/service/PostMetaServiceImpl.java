package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PostMetaDto;
import com.example.demo.entity.Post;
import com.example.demo.entity.PostMeta;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.PostMetaRepository;
import com.example.demo.repository.PostRepository;

@Service
public class PostMetaServiceImpl implements PostMetaService {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private PostMetaRepository postMetaRepository;

	@Override
	public PostMetaDto addPostMeta(PostMetaDto postMetaDto) {
		Post orElseThrow = postRepository.findByPostId(postMetaDto.getPostId())
				.orElseThrow(() -> new UserNotFoundException("invalid post is"));
		PostMeta postMeta = new PostMeta();
		postMeta.setPost(orElseThrow);
		BeanUtils.copyProperties(postMetaDto, postMeta);
		PostMeta savePostMeta = postMetaRepository.save(postMeta);
		PostMetaDto postMetaDto2 = new PostMetaDto();
		BeanUtils.copyProperties(savePostMeta, postMetaDto2);
		return postMetaDto2;
	}

	@Override
	public List<PostMetaDto> getPostMeta(Long postId) {
		Optional<Post> findById = postRepository.findById(postId);
		if (findById.isEmpty()) {
			throw new UserNotFoundException("invalid post id");
		}
		Post post = findById.get();
		List<PostMeta> postMetaList = post.getPostMetaList();
		List<PostMetaDto> postMetaDtoList = new ArrayList<>();
		postMetaList.forEach(i -> {
			PostMetaDto postMetaDto = new PostMetaDto();
			BeanUtils.copyProperties(i, postMetaDto);
			postMetaDtoList.add(postMetaDto);

		});

		return postMetaDtoList;
	}

	@Override
	public PostMetaDto updatePostMetaDto(PostMetaDto postMetaDto) {
		Optional<PostMeta> findById = postMetaRepository.findById(postMetaDto.getPostMetaId());
		if (findById.isPresent()) {
			BeanUtils.copyProperties(postMetaDto, findById.get());
			postMetaRepository.save(findById.get());
			BeanUtils.copyProperties(findById.get(), postMetaDto);
			return postMetaDto;

		}
		throw new UserNotFoundException("invalid id");
	}

	@Override
	public PostMetaDto deletePostMetaDto(Long postMetaId) {
		Optional<PostMeta> findById = postMetaRepository.findById(postMetaId);
		if (findById.isPresent()) {
			postMetaRepository.deleteById(postMetaId);
			return new PostMetaDto();

		}
		throw new UserNotFoundException("invalid id");
	}
}
