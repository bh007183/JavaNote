package com.diary.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
import com.diary.models.UserDao;


@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
//	Generic
	
	@GetMapping("/users")
	public List<User> getUsers(){
		var users = (List<User>) userDao.findAll();
		if(users.isEmpty()) {
			throw new NoDataFoundException();
		}
		return users;
	}
	
	
	@PostMapping("/user")

	public ResponseEntity<Object> postUser(@RequestBody @Valid User user){
		
		var exists = userDao.findByEmail(user.getEmail());
		if(exists.isPresent()) {
			throw new AccountExistsException();
		}
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		
		return ResponseEntity.accepted().body(userDao.save(user));
	
		
		
	   
	}
	
// Detail
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id){
			return userDao.findById(id).orElseThrow(() -> new NotFoundException(id));
		
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Object>deleteUser(@PathVariable Long id){
		if(userDao.existsById(id)) {
			   userDao.deleteById(id);
			   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		   }else {
			   throw new NoItemToDeleteException();
		   }
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable Long id,  @RequestBody User userDetails){
		var user = userDao.findById(id).orElseThrow(() -> new NotFoundException(id));;
		
		user.setPassword(userDetails.getPassword());
		user.setEmail(userDetails.getEmail());
		
		final User updatedUser = userDao.save(user);
		return ResponseEntity.ok(updatedUser);		
	}

}
