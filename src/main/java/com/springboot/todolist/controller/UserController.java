package com.springboot.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.todolist.entity.AuthenticationRequest;
import com.springboot.todolist.entity.AuthenticationResponse;
import com.springboot.todolist.entity.Card;
import com.springboot.todolist.entity.User;
import com.springboot.todolist.service.ICardService;
import com.springboot.todolist.service.IUserService;
import com.springboot.todolist.service.MyUserDetailsService;
import com.springboot.todolist.util.JwtUtil;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	private IUserService userService;
	private ICardService cardService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	public UserController(IUserService userService, ICardService cardService) {
		super();
		this.userService = userService;
		this.cardService = cardService;
	}

	@PostMapping
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.registerUser(user), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
							authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@GetMapping("/admin")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable long id) {
		return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
	}

	@DeleteMapping("/admin/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
		return new ResponseEntity<String>("User deleted successfully!", HttpStatus.OK);
	}

	@PutMapping("/admin/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable long id) {
		return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
	}

	@PostMapping("/user/cards/{id}")
	public ResponseEntity<Card> saveCard(@RequestBody Card card, @PathVariable long id) {
		return new ResponseEntity<Card>(cardService.saveCard(card, id), HttpStatus.CREATED);
	}

	@GetMapping("/admin/cards")
	public List<Card> getToDoList() {
		return cardService.getToDoList();
	}

	@GetMapping("/admin/cards/{id}")
	public ResponseEntity<Card> getCardById(@PathVariable long id) {
		return new ResponseEntity<Card>(cardService.getCardById(id), HttpStatus.OK);
	}

	@DeleteMapping("/user/cards/{id}")
	public ResponseEntity<String> deleteCard(@PathVariable long id) {
		cardService.deleteCard(id);
		return new ResponseEntity<String>("Card deleted successfully!", HttpStatus.OK);
	}

	@PutMapping("/user/cards/{id}")
	public ResponseEntity<Card> updateCard(@PathVariable long id, @RequestBody Card card) {
		return new ResponseEntity<Card>(cardService.updateCard(card, id), HttpStatus.OK);
	}

	@PutMapping("/user/cards/{id}/status/{status}/")
	public ResponseEntity<Card> changeStatus(@PathVariable long id, @PathVariable int status) {
		return new ResponseEntity<Card>(cardService.changeStatus(id, status), HttpStatus.OK);
	}

	@PutMapping("/user/cards/{id}/priority/{priority}")
	public ResponseEntity<Card> changePriority(@PathVariable long id, @PathVariable int priority) {
		return new ResponseEntity<Card>(cardService.changePriority(id, priority), HttpStatus.OK);
	}

	@PutMapping("/user/cards/{id}/category/{category}")
	public ResponseEntity<Card> changeCategory(@PathVariable long id, @PathVariable String category) {
		return new ResponseEntity<Card>(cardService.changeCategory(id, category), HttpStatus.OK);
	}

	@GetMapping("/user/{id}/cards")
	public List<Card> getUserCards(@PathVariable long id) {
		return userService.getUserCards(id);
	}
}
