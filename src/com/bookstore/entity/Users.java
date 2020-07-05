package com.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "users", catalog = "bookstoredb")
@NamedQueries({
	@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u ORDER BY u.fullName"),
	@NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE email = :email"),
	@NamedQuery(name = "Users.countAll", query = "SELECT Count(*) FROM Users u"),
	@NamedQuery(name = "Users.checkLogin", query = "SELECT u FROM Users u WHERE u.email = :email AND u.password = :pass")
})
public class Users {
	private Integer userId;
	private String email;
	private String password;
	private String fullName;
	
	public Users() {
		
	}
	
	public Users(Integer userId, String email, String fullName, String password) {
		this(email, fullName, password);
		this.userId = userId;
	}
	
	public Users(String email, String fullName, String password) {
		this.email = email;
		this.fullName = fullName;
		this.password = password;
	}
	
	public Users(Integer userId, String email, String fullName) {
		this.userId = userId;
		this.email = email;
		this.fullName = fullName;
	}

	@Column(name = "user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	@Column(name = "full_name")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
