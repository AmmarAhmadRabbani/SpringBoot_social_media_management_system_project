package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CategoryDto;

public interface CategoryService {
	public CategoryDto addCategory(CategoryDto categoryDto);

	public CategoryDto updateCategory(CategoryDto categoryDto);

	public CategoryDto deleteCategory(int id);

	public List<CategoryDto> getCategory(Long postId);

}
