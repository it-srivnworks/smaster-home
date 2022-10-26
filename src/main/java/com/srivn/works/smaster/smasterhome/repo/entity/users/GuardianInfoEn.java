package com.srivn.works.smaster.smasterhome.repo.entity.users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.srivn.works.smaster.smasterhome.repo.entity.util.ClsnValEn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "GUARDIANINFO")
public class GuardianInfoEn extends UserInfoEn {

	@Column(name = "mobile")
	private String mobile;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "primaryAddress")
	private AddressInfoEn primaryAddress;

	@ManyToOne
	@JoinColumn(name = "guardianType")
	private ClsnValEn guardianType;

	public GuardianInfoEn() {
		super();
	}
}
