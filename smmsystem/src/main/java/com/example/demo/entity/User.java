package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "smms_user_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false, precision = 19)
	private Long userId;
	@Column(name = "user_first_name", nullable = false, precision = 20)
	private String firstName;
	@Column(name = "user_middle_name", precision = 20)
	private String middleName;
	@Column(name = "user_last_name", nullable = false, precision = 20)
	private String lastName;
	@Column(name = "user_mobile_number", unique = true, nullable = false, precision = 15)
	private String mobileNumber;
	@Column(name = "user_email_id", unique = true, nullable = false, precision = 50)
	private String emailId;
	@Column(name = "user_password", nullable = false, precision = 32)
	private String password;
	@CreationTimestamp
	@Column(name = "user_registered_at", updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
	private LocalDateTime registeredAt;
	@CreationTimestamp
	@Column(name = "user_last_login")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH:mm:ss")
	private LocalDateTime lastLogin;
	@Column(name = "user_intro", precision = 20)
	private String intro;
	@Column(name = "user_profile", precision = 50)
	private String profile;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Post> postList;

}
