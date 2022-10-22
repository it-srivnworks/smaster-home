package com.srivn.works.smaster.smasterhome.repo.entity.users;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class UserDetailsEn extends UserInfoEn{

	@Column(name = "dob")
	private Date dob;
	
	@Column(name = "profilePic")
	private byte[] profilePic;
	
	@Column(name = "mobile")
	private String mobile;
	
	@OneToOne(mappedBy = "userInfoEn", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
	private AddressInfoEn primaryAddress;
	
	@Column(name = "currentStatus")
	private int currentStatus;
	
	@Column(name = "inDate")
	private Date inDate;
	
	@Column(name = "outDate")
	private Date outDate;
	
	
	
	public UserDetailsEn() {
		super();
	}



	public UserDetailsEn(Date dob, byte[] profilePic, String mobile, AddressInfoEn primaryAddress, int currentStatus,
			Date inDate, Date outDate) {
		super();
		this.dob = dob;
		this.profilePic = profilePic;
		this.mobile = mobile;
		this.primaryAddress = primaryAddress;
		this.currentStatus = currentStatus;
		this.inDate = inDate;
		this.outDate = outDate;
	}



	
}
