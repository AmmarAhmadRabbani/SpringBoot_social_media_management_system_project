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

import com.example.demo.dto.PostCommentDto;
import com.example.demo.response.SuccessResponse;
import com.example.demo.service.PostCommentService;

@RestController
public class PostCommentController {
	@Autowired
	private PostCommentService postCommentService;

	@PostMapping("/addPostComment")
	public ResponseEntity<SuccessResponse> addPostComment(@RequestBody PostCommentDto postCommentDto) {
		PostCommentDto addPostComment = postCommentService.addPostComment(postCommentDto);
		if (addPostComment != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "added successfully", addPostComment),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "something went wrong", null), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getPostComment/{postId}")
	public ResponseEntity<SuccessResponse> getPostComment(@PathVariable Long postId) {
		List<PostCommentDto> postComment = postCommentService.getPostComment(postId);
		if (postComment != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "successfully get", postComment), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "something went wrong", null), HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/updatePostComment/{postCommentId}")
	public ResponseEntity<SuccessResponse> updatePostComment(@RequestBody PostCommentDto postCommentDto) {
		PostCommentDto updatePostComment = postCommentService.updatePostComment(postCommentDto);
		if (updatePostComment != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "successfully updated", updatePostComment),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "updation failed", null), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/deletePostComment/{postCommentId}")
	public ResponseEntity<SuccessResponse> deletePostComment(@PathVariable Long postCommentId) {
		PostCommentDto deletePostComment = postCommentService.deletePostComment(postCommentId);
		if (deletePostComment != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "deleted", deletePostComment), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "deletion failed", null), HttpStatus.BAD_REQUEST);
	}

}
