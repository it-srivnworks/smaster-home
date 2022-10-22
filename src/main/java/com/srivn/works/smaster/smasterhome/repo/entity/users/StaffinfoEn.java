package com.srivn.works.smaster.smasterhome.repo.entity.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.srivn.works.smaster.smasterhome.repo.entity.util.ClsnValEn;

@Entity
@Table(name = "STAFFINFO")
public class StaffinfoEn extends UserDetailsEn{

	@ManyToOne
	@JoinColumn(name = "profile_title")
	private ClsnValEn profileTitle;
	
	@Column(name = "profdescription")
	private String profileDescription;
	
	@ManyToOne
	@JoinColumn(name = "deptid")
	private ClsnValEn deptID;
	
	public StaffinfoEn() {
		super();
	}

	public StaffinfoEn(ClsnValEn profileTitle, String profileDescription, ClsnValEn deptID) {
		super();
		this.profileTitle = profileTitle;
		this.profileDescription = profileDescription;
		this.deptID = deptID;
	}

	
	
}
