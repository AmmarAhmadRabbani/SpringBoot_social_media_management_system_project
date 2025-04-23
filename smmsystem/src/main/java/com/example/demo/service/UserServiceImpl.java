package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	@Override
	public UserDto addUser(UserDto userDto) {
		if (userDto != null) {
			User user = new User();
			BeanUtils.copyProperties(userDto, user);
			userRepository.save(user);
			BeanUtils.copyProperties(user, userDto);
			return userDto;

		}
		throw new UserNotFoundException("user not added");
	}

	@Override
	public List<UserDto> getUser() {
		List<UserDto> userList = new ArrayList<>();
		List<User> findAll = userRepository.findAll();
		if (!findAll.isEmpty()) {
			findAll.forEach(i -> {
				UserDto userDto = new UserDto();
				BeanUtils.copyProperties(i, userDto);
				userList.add(userDto);
			});
			return userList;
		}
		throw new UserNotFoundException("invalid user");
	}

	@Override
	public UserDto getUserById(Long userId) {
		Optional<User> findByUserId = userRepository.findByUserId(userId);
		if (findByUserId.isPresent()) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(findByUserId.get(), userDto);
			return userDto;
		}

		throw new UserNotFoundException("invalid user id");
	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		Optional<User> findById = userRepository.findById(userDto.getUserId());
		if (findById.isPresent()) {
			BeanUtils.copyProperties(userDto, findById.get());
			userRepository.save(findById.get());
			BeanUtils.copyProperties(findById.get(), userDto);
			return userDto;
		}
		throw new UserNotFoundException("invalid user");
	}

	@Override
	public UserDto deleteUser(Long userId) {
		Optional<User> findById = userRepository.findById(userId);
		if (findById.isPresent()) {
			userRepository.deleteById(userId);
			return new UserDto();
		}
		throw new UserNotFoundException("invalid id");
	}

}
