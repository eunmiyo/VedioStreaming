package com.streaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.streaming.constant.Membership;
import com.streaming.constant.User_Role;
import com.streaming.dto.UserInfoDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Table(name="userinfo") //테이블명
@Getter
@Setter
@ToString
public class UserInfo extends BaseEntity {
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="user_name")
	private String name;
	
	@Column(unique=true)
	private String email;
	
	@Column(name="user_pw")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private User_Role role;
	
	@Enumerated(EnumType.STRING)
	private Membership membership;
	
	public static UserInfo createUserInfo(UserInfoDto userinfoDto , PasswordEncoder passwordEncoder) {
		UserInfo userInfo = new UserInfo();
		userInfo.setName(userinfoDto.getName());
		userInfo.setEmail(userinfoDto.getEmail());
		
		//비밀번호 암호화
		String password = passwordEncoder.encode(userinfoDto.getPassword());
		userInfo.setPassword(password);
		
		userInfo.setRole(User_Role.USER);
		userInfo.setMembership(Membership.BASIC);
		
		return userInfo;
		
	}
}
