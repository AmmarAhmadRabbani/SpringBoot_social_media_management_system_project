package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Category;
import com.example.demo.entity.Post;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Category save(Optional<Post> findByPostId);

}
