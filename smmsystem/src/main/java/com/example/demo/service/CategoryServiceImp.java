package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Post;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.PostRepository;

@Service
public class CategoryServiceImp implements CategoryService {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		Optional<Category> findById2 = categoryRepository.findById(categoryDto.getId());
		Post post = postRepository.findById(categoryDto.getPostId())
				.orElseThrow(() -> new RuntimeException("Not FOund"));
		if (findById2.isEmpty()) {
			Category category = new Category();
			BeanUtils.copyProperties(categoryDto, category);
			category.setPostList(List.of(post));
			Category save = categoryRepository.save(category);
			return categoryDto;

		}
		throw new UserNotFoundException("post id  not found");
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto) {
		Optional<Category> findById = categoryRepository.findById(categoryDto.getId());
		if (findById.isPresent()) {
			BeanUtils.copyProperties(categoryDto, findById.get());
			categoryRepository.save(findById.get());
			BeanUtils.copyProperties(findById.get(), categoryDto);
			return categoryDto;

		}
		throw new UserNotFoundException("invalid id");
	}

	@Override
	public CategoryDto deleteCategory(int id) {
		Optional<Category> findById = categoryRepository.findById(id);
		if (findById.isPresent()) {
			categoryRepository.deleteById(id);
			return new CategoryDto();

		}
		throw new UserNotFoundException("invalid id");
	}

	@Override
	public List<CategoryDto> getCategory(Long postId) {
		Optional<Post> findById = postRepository.findById(postId);
		if (findById.isEmpty()) {
			throw new UserNotFoundException("invalid id");

		}
		Post post = findById.get();
		List<Category> categoList = post.getCategoList();
		List<CategoryDto> categoryDtoList = new ArrayList<>();
		categoList.forEach(i -> {
			CategoryDto categoryDto = new CategoryDto();
			BeanUtils.copyProperties(i, categoryDto);
			categoryDtoList.add(categoryDto);
		});
		return categoryDtoList;
	}

}
