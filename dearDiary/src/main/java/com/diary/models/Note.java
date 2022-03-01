package com.diary.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Note {
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Long id;
	
	
	private String note;
	private Date date_added;
	
	@PrePersist
	public void setDate() {
		this.date_added = new Date();
	}
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	

}
