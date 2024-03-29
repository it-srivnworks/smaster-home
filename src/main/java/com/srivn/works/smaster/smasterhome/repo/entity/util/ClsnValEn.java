package com.srivn.works.smaster.smasterhome.repo.entity.util;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "CLSNVAL")
public class ClsnValEn {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "clsnValID")
	private int clsnValID;
	
	@Column(name = "clsnVal")
	private String value;
	
}
