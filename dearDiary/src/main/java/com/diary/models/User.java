package com.diary.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
public class User {
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Long id;
	
	@Column(unique=true)
	private String email;
	
	private String password;
	
	private Date date_added;
	
	public User () {
		
	}
	public User(String email, String password) {
		this.email = email;
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}
	@PrePersist
	public void setDate() {
		this.date_added = new Date();
	}
	
	@OneToMany(mappedBy="note")
	private Set<Note> notes;
	
	@Override
	public String toString(){
		return this.email;
		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return id;
	}

	public Set<Note> getNotes() {
		return notes;
	}
	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}
	
//	
//	if (BCrypt.checkpw(candidate_password, stored_hash))
//	    System.out.println("It matches");
//	else
//	    System.out.println("It does not match");
}
