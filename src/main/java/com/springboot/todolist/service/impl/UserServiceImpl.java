package com.springboot.todolist.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.todolist.entity.Card;
import com.springboot.todolist.entity.User;
import com.springboot.todolist.exception.ResourceNotFoundException;
import com.springboot.todolist.repository.UserRepository;
import com.springboot.todolist.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User registerUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Long login(String username) {
		User existingUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User with username: " + username + " not found"));
		return existingUser.getId();
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));
	}

	@Override
	public void deleteUser(long id) {
		userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));
		userRepository.deleteById(id);
	}

	@Override
	public User updateUser(User user, long id) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));

		existingUser.setUsername(user.getUsername());
		existingUser.setPassword(user.getPassword());

		userRepository.save(existingUser);

		return existingUser;
	}

	@Override
	public List<Card> getUserCards(long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));
		return user.getCards();
	}
}
