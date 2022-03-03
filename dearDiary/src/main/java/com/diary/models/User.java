package com.diary.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
public class User {
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Long id;
	
	@Column(unique=true)
	@NotNull
	private String email;
	
	@NotNull
//	@Size(min = 8, max = 20)
//	@Pattern(regexp = ("^(?=.*[a-z])"), message ="Password must contain lower case characers")
//	@Pattern(regexp = ("^(?=.*[A-Z])"), message ="Password must contain upper case characers")
//	@Pattern(regexp = ("^(?=.*[!@#&()–[{}]:;',?/*~$^+=<>])"), message ="Password must contain special characters")
	
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
	public boolean verifyPassword(String password) {
		return BCrypt.checkpw(password, this.password);
	}
	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
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
