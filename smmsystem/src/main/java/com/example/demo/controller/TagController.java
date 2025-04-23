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

import com.example.demo.dto.TagDto;
import com.example.demo.response.SuccessResponse;
import com.example.demo.service.TagService;

@RestController
public class TagController {
	@Autowired
	private TagService tagService;

	@PostMapping("/addTag")
	public ResponseEntity<SuccessResponse> addTag(@RequestBody TagDto tagDto) {
		TagDto addTag = tagService.addTag(tagDto);
		if (addTag != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "successfully added", addTag), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "adding fail", null), HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/updateTag/{tagId}")
	public ResponseEntity<SuccessResponse> updateTag(@RequestBody TagDto tagDto) {
		TagDto updateTag = tagService.updateTag(tagDto);
		if (updateTag != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "updated", updateTag), HttpStatus.OK);

		}
		return new ResponseEntity<>(new SuccessResponse(true, "updation failed", null), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/deleteTag{tagId}")
	public ResponseEntity<SuccessResponse> deleteTag(@PathVariable Long tagId) {
		TagDto deleteTag = tagService.deleteTag(tagId);
		if (deleteTag != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "deleted", deleteTag), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "not deleted", null), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getTag/{postId}")
	public ResponseEntity<SuccessResponse> getTag(@PathVariable Long postId) {
		List<TagDto> tag = tagService.getTag(postId);
		if (tag != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "get successfully", tag), HttpStatus.OK);

		}
		return new ResponseEntity<>(new SuccessResponse(true, "not get", null), HttpStatus.BAD_REQUEST);
	}

}
