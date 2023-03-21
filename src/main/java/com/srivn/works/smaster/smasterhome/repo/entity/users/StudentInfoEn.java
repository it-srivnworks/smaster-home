package com.srivn.works.smaster.smasterhome.repo.entity.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "STUDENTINFO")
public class StudentInfoEn extends UserDetailsEn {

	@Column(name = "rollno")
	private int rollNo;

	@ManyToOne
	@JoinColumn(name = "pguardian")
	private GuardianInfoEn pguardian;
	
	@ManyToOne
	@JoinColumn(name = "sguardian")
	private GuardianInfoEn sguardian;
	
	public StudentInfoEn() {
		super();
	}
}
