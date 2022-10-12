package com.srivn.works.smaster.smasterhome.repo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "userinfo")
public class UserInfoEn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int ID;
    
    @Column(name = "username")
    private String userName;
    
    @Column(name = "useremail")
	private String userEmail;
    
    @Column(name = "userage")
	private int userAge;

}
