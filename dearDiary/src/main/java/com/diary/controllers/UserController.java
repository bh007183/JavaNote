package com.diary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diary.models.User;
import com.diary.models.UserDao;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
//	Generic
	
	@GetMapping("/users")
	public ResponseEntity<Object> getUsers(){
		return  ResponseEntity.accepted().body(userDao.findAll());
	}
	
	@PostMapping("/user")
	public ResponseEntity<Object> postUser(@RequestBody User user){
		System.out.println(user);
		try {
			User persistedUser = userDao.save(user);
			return  ResponseEntity.accepted().body(persistedUser);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e);
		}
	}
	
// Detail
	
	
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Object> getUser(@PathVariable Long id){
		System.out.println(id);
		try {
			return ResponseEntity.accepted().body(userDao.findById(id));
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e);
		}
		
		
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Object>deleteUser(@PathVariable Long id){
		try {
			userDao.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e);
		}
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable Long id,  @RequestBody User userDetails){
		var user = userDao.findById(id).get();
		
		user.setPassword(userDetails.getPassword());
		user.setEmail(userDetails.getEmail());
		
		final User updatedUser = userDao.save(user);
		return ResponseEntity.ok(updatedUser);		
	}

}
