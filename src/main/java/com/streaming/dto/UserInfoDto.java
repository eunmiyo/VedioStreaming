package com.streaming.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.streaming.entity.UserInfo;

import lombok.Getter;
import lombok.Setter;

//회원가입으로부터 넘어오는 가입정보를 담을 DTO
@Getter
@Setter
public class UserInfoDto {
	
	private Long id;
	
	@NotBlank(message="이름은 필수 입력 값입니다!")
	private String name;
	
	@NotEmpty(message="이메일은 필수 입력 값입니다!")
	@Email(message="이메일 형식으로 입력해주세요!")
	private String email;
	
	@NotBlank(message="비밀번호는 필수 입력 값입니다!")
	@Length(min = 6, max = 11, message="비밀번호는 6자 이상, 11자 이하로 입력해주세요")
	private String password;
	
	//회원수정
	public UserInfo updateUserInfo(UserInfo userInfo, PasswordEncoder passwordEncoder) {
		userInfo.setPassword(passwordEncoder.encode(this.password));
		userInfo.setName(name);
		
		return userInfo;
	}
	
}
