package com.diary.models;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class User implements UserDetails{

	
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
	@JsonView
	private String password;
	
	
	private Date date_added;
	
	public User () {
		
	}
	
	@PrePersist
	public void setDate() {
		this.date_added = new Date();
	}
	
	@OneToMany(mappedBy="note")
	private List<Note> notes;
	
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

	public List<Note> getNotes() {
		return this.notes;
	}
	public void setNotes(Note note) {
		this.notes.add(note);
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	
//	
//	if (BCrypt.checkpw(candidate_password, stored_hash))
//	    System.out.println("It matches");
//	else
//	    System.out.println("It does not match");
}
