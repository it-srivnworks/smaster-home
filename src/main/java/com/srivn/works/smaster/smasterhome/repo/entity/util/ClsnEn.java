package com.srivn.works.smaster.smasterhome.repo.entity.util;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CLSN")
public class ClsnEn {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "clsnid")
	private int clsnID;
	
	@Column(name = "clsnkey")
	private String clsnKey;
	
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "clsnid")
    private List<ClsnValEn> ClsnVals;
}
