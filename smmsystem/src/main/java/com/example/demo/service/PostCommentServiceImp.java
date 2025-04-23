package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PostCommentDto;
import com.example.demo.entity.Post;
import com.example.demo.entity.PostComment;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.PostCommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Service
public class PostCommentServiceImp implements PostCommentService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private PostCommentRepository postCommentRepository;

	@Override
	public PostCommentDto addPostComment(PostCommentDto postCommentDto) {
		Optional<User> findByUserId = userRepository.findByUserId(postCommentDto.getUserId());
		if (findByUserId.isPresent()) {
			Optional<Post> findById = postRepository.findById(postCommentDto.getPostId());
			if (findById.isPresent()) {
				PostComment postComment = new PostComment();
				postComment.setPost(findById.get());
				BeanUtils.copyProperties(postCommentDto, postComment);
				postCommentRepository.save(postComment);
				BeanUtils.copyProperties(postComment, postCommentDto);
				return postCommentDto;

			}
			throw new UserNotFoundException("invalid");
		}
		throw new UserNotFoundException("invalid post");
	}

	@Override
	public List<PostCommentDto> getPostComment(Long postId) {
		Optional<Post> findById = postRepository.findById(postId);
		if (findById.isEmpty()) {
			throw new UserNotFoundException("post comment is not available");
		}
		Post post = findById.get();
		List<PostComment> postCommentlist = post.getPostCommentlist();
		List<PostCommentDto> postCommentDtoList = new ArrayList<>();
		postCommentlist.forEach(i -> {
			PostCommentDto postCommentDto = new PostCommentDto();
			BeanUtils.copyProperties(i, postCommentDto);
			postCommentDtoList.add(postCommentDto);
		});
		return postCommentDtoList;
	}

	@Override
	public PostCommentDto updatePostComment(PostCommentDto postCommentDto) {
		Optional<PostComment> findById = postCommentRepository.findById(postCommentDto.getPostCommentId());
		if (findById.isPresent()) {
			BeanUtils.copyProperties(postCommentDto, findById.get());
			postCommentRepository.save(findById.get());
			BeanUtils.copyProperties(findById.get(), postCommentDto);
			return postCommentDto;

		}
		throw new UserNotFoundException("invalid post id");
	}

	@Override
	public PostCommentDto deletePostComment(Long postCommentId) {
		Optional<PostComment> findById = postCommentRepository.findById(postCommentId);
		if (findById.isPresent()) {
			postCommentRepository.deleteById(postCommentId);
			return new PostCommentDto();
		}
		throw new UserNotFoundException("invalid id");
	}

}
