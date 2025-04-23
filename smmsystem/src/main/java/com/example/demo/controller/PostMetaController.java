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

import com.example.demo.dto.PostMetaDto;
import com.example.demo.response.SuccessResponse;
import com.example.demo.service.PostMetaService;

@RestController
public class PostMetaController {
	@Autowired
	private PostMetaService postMetaService;

	@PostMapping("/addPostMeta")
	public ResponseEntity<SuccessResponse> addPostMeta(@RequestBody PostMetaDto postMetaDto) {
		PostMetaDto addPostMeta = postMetaService.addPostMeta(postMetaDto);
		if (addPostMeta != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "successfully added", addPostMeta), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "something went wrong", null), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getPostMeta/{postId}")
	public ResponseEntity<SuccessResponse> getPostMeta(@PathVariable Long postId) {
		List<PostMetaDto> postMeta = postMetaService.getPostMeta(postId);
		if (postMeta != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "successfull", postMeta), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "failed", null), HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/updatePostMetaDto{postMetaId}")
	public ResponseEntity<SuccessResponse> updatePostMetaDto(@RequestBody PostMetaDto postMetaDto) {
		PostMetaDto updatePostMetaDto = postMetaService.updatePostMetaDto(postMetaDto);
		if (updatePostMetaDto != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "updated", updatePostMetaDto), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "updation fialed", null), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/deletePostMetaDto/{postMetaId}")
	public ResponseEntity<SuccessResponse> deletePostMetaDto(@PathVariable Long postMetaId) {
		PostMetaDto deletePostMetaDto = postMetaService.deletePostMetaDto(postMetaId);
		if (deletePostMetaDto != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "deleted succesfully", deletePostMetaDto),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "failed", null), HttpStatus.BAD_REQUEST);

	}

}
