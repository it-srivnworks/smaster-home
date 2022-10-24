package com.srivn.works.smaster.smasterhome.repo.entity.users;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "GUARDIANINFO")
public class GuardianInfoEn extends UserInfoEn{

	@Column(name = "mobile")
	private String mobile;

	@OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="primary_addresID")
	private AddressInfoEn primaryAddress;
	
	
	
}
