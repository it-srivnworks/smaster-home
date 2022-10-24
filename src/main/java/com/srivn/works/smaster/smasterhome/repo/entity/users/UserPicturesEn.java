package com.srivn.works.smaster.smasterhome.repo.entity.users;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.srivn.works.smaster.smasterhome.repo.entity.util.ClsnValEn;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "USERPICTURES")
public class UserPicturesEn {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "picID")
    private long id;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] profilePic;

	@Column(name = "active")
	private int active;

	public UserPicturesEn(byte[] profilePic, int active) {
		super();
		this.profilePic = profilePic;
		this.active = active;
	}

	
	
}
