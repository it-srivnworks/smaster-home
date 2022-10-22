package com.srivn.works.smaster.smasterhome.repo.entity.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "USERINFO")
public class UserInfoEn {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userID")
	private int userID;

	@Column(name = "userEmail")
	private String userEmail;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	
	public UserInfoEn() {
		super();
	}


	public UserInfoEn(int userID, String userEmail, String title, String firstName, String lastName) {
		super();
		this.userID = userID;
		this.userEmail = userEmail;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	
	

	
}
