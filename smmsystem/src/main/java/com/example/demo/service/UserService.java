package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDto;

public interface UserService {
	public UserDto addUser(UserDto userDto);

	public List<UserDto> getUser();

	public UserDto getUserById(Long userId);

	public UserDto updateUser(UserDto userDto);

	public UserDto deleteUser(Long userId);

}
