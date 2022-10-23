package com.srivn.works.smaster.smasterhome.repo.entity.users;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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



	protected UserDetailsEn(String userEmail, String title, String firstName, String lastName) {
		super(userEmail, title, firstName, lastName);
		// TODO Auto-generated constructor stub
	}


	public void setPrimaryAddress(AddressInfoEn addressInfoEn) {
        this.primaryAddress = addressInfoEn;
        this.primaryAddress.setUserInfoEn(this); // setting the parent class as the value for the child instance
    }




	
}
