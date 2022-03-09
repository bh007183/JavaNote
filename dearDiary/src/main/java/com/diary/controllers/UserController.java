package com.diary.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diary.exceptions.AccountExistsException;
import com.diary.exceptions.NoDataFoundException;
import com.diary.exceptions.NoItemToDeleteException;
import com.diary.exceptions.NotFoundException;
import com.diary.models.User;
import com.diary.services.UserServiceInterface;


@RestController
@RequestMapping("/api")


public class UserController {
	
	@Autowired
	private UserServiceInterface userService;
	

	
//	Generic
	
	@GetMapping("/users")
	public List<User> findAllUsers(){
		return userService.findAllUsers();
	}
	
	
	@PostMapping("/user")

	public ResponseEntity<Object> addUser(@RequestBody @Valid User user){
	   return userService.addUser(user);
	   
	}
	
// Detail
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id){
			return userService.getUser(id);
		
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Object>deleteUser(@PathVariable Long id){
		return userService.deleteUser(id);
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable Long id,  @RequestBody User userDetails){
		return userService.updateUser(id, userDetails);
	}
	
	
}


