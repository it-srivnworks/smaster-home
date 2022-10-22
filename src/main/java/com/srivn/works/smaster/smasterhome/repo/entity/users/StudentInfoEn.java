package com.srivn.works.smaster.smasterhome.repo.entity.users;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "STUDENTINFO")
public class StudentInfoEn extends UserDetailsEn {

	@Column(name = "rollno")
	private int rollNo;
	
	@OneToMany(cascade = CascadeType.DETACH)
	@JoinColumn(name = "userID")
	private List<GuardianInfoEn> guardians;

	public StudentInfoEn() {
		super();
	}

	public StudentInfoEn(List<GuardianInfoEn> guardians) {
		super();
		this.guardians = guardians;
	}

	

}
