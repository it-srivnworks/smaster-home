package com.srivn.works.smaster.smasterhome.repo.entity.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.srivn.works.smaster.smasterhome.repo.entity.util.ClsnValEn;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "STAFFINFO")
public class  StaffInfoEn  extends UserDetailsEn{

	@ManyToOne
	@JoinColumn(name = "profile_title")
	private ClsnValEn profileTitle;
	
	@Column(name = "profdescription")
	private String profileDescription;
	
	@ManyToOne
	@JoinColumn(name = "deptid")
	private ClsnValEn deptID;

	public StaffInfoEn() {
		super();
	}

	@Builder
	public StaffInfoEn(String userEmail, String title, String firstName, String lastName,int currentStatus) {
		super(userEmail, title, firstName, lastName,currentStatus);
		// TODO Auto-generated constructor stub
	}
	
	
}
