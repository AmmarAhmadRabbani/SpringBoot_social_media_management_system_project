package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PostMeta;

@Repository
public interface PostMetaRepository extends JpaRepository<PostMeta, Long> {
	Optional<PostMeta> findById(Long postId);

}
