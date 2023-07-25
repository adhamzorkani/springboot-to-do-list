package com.springboot.todolist.service;

import java.util.List;

import com.springboot.todolist.entity.Card;

public interface ICardService {

	Card saveCard(Card card, long id);

	List<Card> getToDoList();
	
	Card getCardById(long id);

	void deleteCard(long id);

	Card updateCard(Card Card, long id);

	Card changeStatus(long id, int status);
	
	Card changePriority(long id, int priority);
	
	Card changeCategory(long id, String category);
}
