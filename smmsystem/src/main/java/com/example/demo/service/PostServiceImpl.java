package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PostDto;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

	@Override
	public PostDto addPost(PostDto postDto) {
		User orElseThrow = userRepository.findByUserId(postDto.getUserId())
				.orElseThrow(() -> new UserNotFoundException("no user "));
		Post post = new Post();
		post.setUser(orElseThrow);
		BeanUtils.copyProperties(postDto, post);
		postRepository.save(post);
		BeanUtils.copyProperties(post, postDto);
		return postDto;
	}

	@Override
	public List<PostDto> getPost(Long userId) {
		Optional<User> findById = userRepository.findById(userId);
		if (findById.isEmpty()) {
			throw new UserNotFoundException("invalid user id");
		}
		User user = findById.get();
		List<Post> postList = user.getPostList();
		List<PostDto> postDtoList = new ArrayList<>();
		postList.forEach(i -> {
			PostDto postDto = new PostDto();
			BeanUtils.copyProperties(i, postDto);
			postDtoList.add(postDto);
		});

		return postDtoList;
	}

	@Override
	public PostDto updatePostDto(PostDto postDto) {
		Optional<Post> findById = postRepository.findById(postDto.getPostId());
		if (findById.isPresent()) {
			BeanUtils.copyProperties(postDto, findById.get());
			postRepository.save(findById.get());
			BeanUtils.copyProperties(findById.get(), postDto);
			return postDto;
		}
		throw new UserNotFoundException("post updation fialed");
	}

	@Override
	public PostDto deletePostDto(Long postId) {
		Optional<Post> findById = postRepository.findById(postId);
		if (findById.isPresent()) {
			postRepository.deleteById(postId);
			return new PostDto();
		}
		throw new UserNotFoundException("id not present");
	}

}
