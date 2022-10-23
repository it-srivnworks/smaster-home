package com.srivn.works.smaster.smasterhome.repo.entity.users;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.ToString;


@Entity
@Table(name = "USERPICTURES")
public class UserPicturesEn {

	@Id
    @Column(name = "userID")
    private int userID;

	@OneToOne
    @MapsId
    @JoinColumn(name = "userID")
    private UserInfoEn userInfoEn;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] profilePic;

	@Column(name = "active")
	private int active;

	@Override
	public String toString() {
		return "UserPicturesEn [userID=" + userID + ", userInfoEn=" + userInfoEn + ", active=" + active + "]";
	}
	
}
