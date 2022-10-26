package com.srivn.works.smaster.smasterhome.repo.entity.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class UserInfoEn {

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

	@Column(name = "currentstatus")
	private int currentStatus;

	@Column(name = "userType")
	private int userType;

	public UserInfoEn() {
		super();
	}

	public UserInfoEn( String userEmail, String title, String firstName, String lastName,
			int currentStatus,int userType) {
		super();
		this.userEmail = userEmail;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.currentStatus = currentStatus;
		this.userType = userType;
	}

	
	
}
