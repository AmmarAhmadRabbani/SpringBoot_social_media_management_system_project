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

import com.example.demo.dto.CategoryDto;
import com.example.demo.response.SuccessResponse;
import com.example.demo.service.CategoryService;

@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/addCategory")
	public ResponseEntity<SuccessResponse> addCategory(@RequestBody CategoryDto categoryDto) {
		CategoryDto addCategory = categoryService.addCategory(categoryDto);

		if (addCategory != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "successfully added", addCategory), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "something went wrong", null), HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/updateCategory/{id}")
	public ResponseEntity<SuccessResponse> updateCategory(@RequestBody CategoryDto categoryDto) {
		CategoryDto updateCategory = categoryService.updateCategory(categoryDto);
		if (updateCategory != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "updated", updateCategory), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "updation failed", null), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/deleteCategory/{id}")
	public ResponseEntity<SuccessResponse> deleteCategory(@PathVariable int id) {
		CategoryDto deleteCategory = categoryService.deleteCategory(id);
		if (deleteCategory != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "deleted", deleteCategory), HttpStatus.OK);

		}
		return new ResponseEntity<>(new SuccessResponse(true, "try again", null), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getCategory/{postId}")
	public ResponseEntity<SuccessResponse> getCategory(@PathVariable Long postId) {
		List<CategoryDto> category = categoryService.getCategory(postId);
		if (category != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "successful", category), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "failed", null), HttpStatus.BAD_REQUEST);
	}

}
