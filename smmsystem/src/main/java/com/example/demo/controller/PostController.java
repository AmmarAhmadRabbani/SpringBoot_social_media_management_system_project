package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PostDto;
import com.example.demo.response.SuccessResponse;
import com.example.demo.service.PostService;

@RestController
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/addPost")
	public ResponseEntity<SuccessResponse> addPost(@RequestBody PostDto postDto) {
		PostDto addPost = postService.addPost(postDto);
		if (addPost != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "successfully added", addPost), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "something went wrong", null), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getPost/{userId}")
	public ResponseEntity<SuccessResponse> getPost(@PathVariable Long userId) {
		List<PostDto> post = postService.getPost(userId);
		if (post != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "get successfully", post), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "getting job failed", null), HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/updatePostDto/{postId}")
	public ResponseEntity<SuccessResponse> updatePostDto(@RequestBody PostDto postDto) {
		PostDto updatePostDto = postService.updatePostDto(postDto);
		if (updatePostDto != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "successfully updated", updatePostDto),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "updation failed", null), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/deletePostDto/{postId}")
	public ResponseEntity<SuccessResponse> deletePostDto(@PathVariable Long postId) {
		PostDto deletePostDto = postService.deletePostDto(postId);
		if (deletePostDto != null) {

			return new ResponseEntity<>(new SuccessResponse(false, "deleted successfully", deletePostDto),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "deletion failed", null), HttpStatus.BAD_REQUEST);
	}

}
