package com.diary.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class User {
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Long id;
	
	@Column(unique=true)
	@NotNull
	@Email
	
	private String email;
	
	@NotNull
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!^&+=])(?=\\S+$).{8,}$", message="Password must be minimum of 8 charectors in length and contain numbers, upper, lower, and special charectors.")
	
	private String password;
	
	
	private Date date_added;
	
	public User () {
		
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
	
	public void setPassword(String password) {

		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
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
