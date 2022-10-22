package com.srivn.works.smaster.smasterhome.repo.entity.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.srivn.works.smaster.smasterhome.repo.entity.util.ClsnValEn;

@Entity
@Table(name = "ADDRESSINFO")
public class AddressInfoEn {

	@Column(name = "addressLine01")
	private String addressLine01;
	
	@Column(name = "addressLine02")
	private String aAddressLine02;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@ManyToOne
	@JoinColumn(name = "country")
	private ClsnValEn country;
	
	@Column(name = "postCode")
	private String postCode;
	
	@Id
    @Column(name = "userID")
    private Long userID;

	@OneToOne
    @MapsId
    @JoinColumn(name = "userID")
    private UserInfoEn userInfoEn;
	
	public AddressInfoEn() {
		super();
	}

	public AddressInfoEn(String addressLine01, String aAddressLine02, String city, String state, ClsnValEn country,
			String postCode, Long userID, UserInfoEn userInfoEn) {
		super();
		this.addressLine01 = addressLine01;
		this.aAddressLine02 = aAddressLine02;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postCode = postCode;
		this.userID = userID;
		this.userInfoEn = userInfoEn;
	}

		
}
