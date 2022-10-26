package com.srivn.works.smaster.smasterhome.model.users;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginData implements Serializable {

	private static final long serialVersionUID = -7382440974913936855L;
	
	private String userEmail;
	private String userPassword;
}
