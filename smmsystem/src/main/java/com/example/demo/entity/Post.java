package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "smms_post_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "postId")
public class Post implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id", unique = true, nullable = false, precision = 19)
	private Long postId;
	@Column(name = "parent_id", precision = 19)
	private Long parentId;
	@Column(name = "title", precision = 75)
	private String title;
	@Column(name = "meta_title", precision = 100)
	private String metaTile;
	@Column(name = "slug", precision = 100)
	private String slug;
	@Column(name = "summary", precision = 120)
	private String summary;
	@Column(name = "published", precision = 10)
	private Integer published;
	@CreationTimestamp
	@Column(name = "created_date", updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
	private LocalDateTime createdAt;
	@UpdateTimestamp
	@Column(name = "updated_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
	private LocalDateTime updatedAt;
	@CreationTimestamp
	@Column(name = "published_at")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
	private LocalDateTime publishedAT;
	@Column(name = "content")
	private String content;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private User user;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
	private List<PostComment> postCommentlist;
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "postList")
	private List<Tag> tagList;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "postList")
	private List<Category> categoList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
	private List<PostMeta> postMetaList;

}
