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

import com.example.demo.dto.UserDto;
import com.example.demo.response.SuccessResponse;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/addUser")
	public ResponseEntity<SuccessResponse> addUser(@RequestBody UserDto userDto) {
		UserDto addUser = userService.addUser(userDto);
		if (addUser != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "user added successfully", addUser), HttpStatus.OK);

		}
		return new ResponseEntity<>(new SuccessResponse(true, "something went wrong", null), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getUser")
	public ResponseEntity<SuccessResponse> getUser() {
		List<UserDto> user = userService.getUser();
		if (user != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "get successfully", user), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "something went wrong", null), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<SuccessResponse> getUserById(@PathVariable Long userId) {
		UserDto userById = userService.getUserById(userId);
		if (userById != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "get successfully", userById), HttpStatus.OK);

		}
		return new ResponseEntity<>(new SuccessResponse(true, "invalid user id", null), HttpStatus.BAD_REQUEST);

	}

	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<SuccessResponse> updateUser(@RequestBody UserDto userDto) {
		UserDto updateUser = userService.updateUser(userDto);
		if (updateUser != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "successfully updated", updateUser), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "updation failed", null), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<SuccessResponse> deleteUser(@PathVariable Long userId) {
		UserDto deleteUser = userService.deleteUser(userId);
		if (deleteUser != null) {

			return new ResponseEntity<>(new SuccessResponse(false, "deleted successfully", deleteUser), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "not deleted", null), HttpStatus.BAD_REQUEST);

	}

}
