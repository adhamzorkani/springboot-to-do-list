package com.springboot.todolist.service;

import java.util.List;

import com.springboot.todolist.entity.Card;
import com.springboot.todolist.entity.User;

public interface IUserService {

	User registerUser(User user);

	Long login(String name);

	public List<User> getUsers();

	User getUserById(long id);

	void deleteUser(long id);

	User updateUser(User user, long id);
	
	List<Card> getUserCards(long id);
	
}
