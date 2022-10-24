package com.srivn.works.smaster.smasterhome.repo.entity.users;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class UserDetailsEn extends UserInfoEn {

	@Column(name = "dob")
	private Date dob;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "prof_pic")
	private UserPicturesEn userPic;

	@Column(name = "mobile")
	private String mobile;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "primary_addresID")
	private AddressInfoEn primaryAddress;

	@Column(name = "inDate")
	private Date inDate;

	@Column(name = "outDate")
	private Date outDate;

	public UserDetailsEn() {
		super();
	}

	protected UserDetailsEn(String userEmail, String title, String firstName, String lastName, int currentStatus) {
		super(userEmail, title, firstName, lastName, currentStatus);
		// TODO Auto-generated constructor stub
	}

}
