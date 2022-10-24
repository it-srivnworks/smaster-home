package com.srivn.works.smaster.smasterhome.model.users;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.srivn.works.smaster.smasterhome.repo.entity.util.ClsnValEn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressInfo {

	private String addressLine01;
	private String aAddressLine02;
	private String city;
	private String state;
	private String country;
	private String postCode;
	
}
