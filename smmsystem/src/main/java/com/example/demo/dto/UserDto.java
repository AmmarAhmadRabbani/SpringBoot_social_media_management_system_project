package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private Long userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobileNumber;
	private String emailId;
	private String password;
	private String intro;
	private String profile;

}
