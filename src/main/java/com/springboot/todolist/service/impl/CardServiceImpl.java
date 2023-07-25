package com.springboot.todolist.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.todolist.exception.ResourceNotFoundException;
import com.springboot.todolist.entity.Card;
import com.springboot.todolist.entity.User;
import com.springboot.todolist.repository.CardRepository;
import com.springboot.todolist.service.ICardService;
import com.springboot.todolist.service.IUserService;

@Service
public class CardServiceImpl implements ICardService {

	private CardRepository cardRepository;
	private IUserService userService;

	public CardServiceImpl(CardRepository cardRepository, IUserService userService) {
		super();
		this.cardRepository = cardRepository;
		this.userService = userService;
	}

	@Override
	public Card saveCard(Card card, long id) {
		User user = userService.getUserById(id);

		card.setUser(user);
		return cardRepository.save(card);
	}

	@Override
	public List<Card> getToDoList() {
		return cardRepository.findAll();
	}

	@Override
	public Card getCardById(long id) {
		return cardRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Card with id: " + id + " not found"));
	}

	@Override
	public void deleteCard(long id) {
		cardRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Card with id: " + id + " not found"));
		cardRepository.deleteById(id);
	}

	@Override
	public Card updateCard(Card card, long id) {
		Card existingCard = cardRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Card with id: " + id + " not found"));

		existingCard.setTitle(card.getTitle());
		existingCard.setDescription(card.getDescription());
		existingCard.setStatus(card.getStatus());
		existingCard.setPriority(card.getPriority());
		existingCard.setDate(card.getDate());
		existingCard.setUser(card.getUser());

		cardRepository.save(existingCard);

		return existingCard;
	}

	@Override
	public Card changeStatus(long id, int status) {

		Card card = cardRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Card with id: " + id + " not found"));

		card.setStatus(status);

		cardRepository.save(card);

		return card;
	}

	@Override
	public Card changePriority(long id, int priority) {

		Card card = cardRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Card with id: " + id + " not found"));

		card.setPriority(priority);

		cardRepository.save(card);

		return card;
	}

	@Override
	public Card changeCategory(long id, String category) {
		Card card = cardRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Card with id: " + id + " not found"));

		card.setCategory(category);
		;

		cardRepository.save(card);

		return card;
	}

}
