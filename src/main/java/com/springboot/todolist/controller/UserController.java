package com.springboot.todolist.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.todolist.entity.Card;
import com.springboot.todolist.entity.User;
import com.springboot.todolist.models.MyUserDetails;
import com.springboot.todolist.models.request.AuthenticationRequest;
import com.springboot.todolist.models.response.AuthenticationResponse;
import com.springboot.todolist.models.response.ErrorResponse;
import com.springboot.todolist.service.ICardService;
import com.springboot.todolist.service.IUserService;
import com.springboot.todolist.service.MyUserDetailsService;
import com.springboot.todolist.util.JwtUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	IUserService userService;

	@Autowired
	ICardService cardService;

	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtil jwtUtil;

	@PostMapping("/auth/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.registerUser(user), HttpStatus.CREATED);
	}

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

			ResponseCookie jwtCookie = jwtUtil.generateJwtCookie(userDetails);

			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
					.body(new AuthenticationResponse(userDetails.getId(), userDetails.getUsername(),
							userDetails.isActive(), roles));
		} catch (BadCredentialsException e) {
			ErrorResponse errorRes = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid username or Password");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorRes);
		} catch (Exception e) {
			ErrorResponse errorRes = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorRes);
		}

	}

	@GetMapping("/admin/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable long id) {
		return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
	}

	@DeleteMapping("/admin/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
		return new ResponseEntity<String>("User deleted successfully!", HttpStatus.OK);
	}

	@PutMapping("/admin/user/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable long id) {
		return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
	}

	@GetMapping("/admin/cards")
	public List<Card> getToDoList() {
		return cardService.getToDoList();
	}

	@PostMapping("/user/cards/{id}")
	public ResponseEntity<Card> saveCard(@RequestBody Card card, @PathVariable long id) {
		return new ResponseEntity<Card>(cardService.saveCard(card, id), HttpStatus.CREATED);
	}

	@GetMapping("/user/cards/{id}")
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
