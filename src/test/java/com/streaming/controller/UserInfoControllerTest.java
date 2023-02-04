package com.streaming.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.streaming.constant.Membership;
import com.streaming.constant.User_Role;
import com.streaming.dto.UserInfoDto;
import com.streaming.entity.UserInfo;
import com.streaming.service.UserInfoService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class UserInfoControllerTest {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public UserInfo createUserInfo(String email , String password) {
		UserInfoDto userInfo = new UserInfoDto();
		userInfo.setName("전은미");
		userInfo.setEmail(email);
		userInfo.setPassword(password);
		
		UserInfo userInfo2 = UserInfo.createUserInfo(userInfo, passwordEncoder);
		
		return userInfoService.saveUser(userInfo2);
		
	}

	@Test
	@DisplayName("로그인 성공 테스트")
	public void loginSuccessTest() throws Exception {
		String email = "test@email.com";
        String password = "1234";
        this.createUserInfo(email, password);
        mockMvc.perform(formLogin().userParameter("email")
                .loginProcessingUrl("/members/login") //회원가입 메소드를 실행 후에 회원정보로 로그인이 되는지 테스트 진행(해당 url로 로그인 요청)
                .user(email).password(password))
                .andExpect(SecurityMockMvcResultMatchers.authenticated()); //로그인이 성공해서 인증되면 테스트 코드를 통과시킨다.
	}
	
	@Test
	@DisplayName("로그인 실패 테스트")
	public void loginFailTest() throws Exception {
		String email = "test@email.com";
        String password = "1234";
        this.createUserInfo(email, password);
        mockMvc.perform(formLogin().userParameter("email")
                .loginProcessingUrl("/members/login") //회원가입 메소드를 실행 후에 회원정보로 로그인이 되는지 테스트 진행(해당 url로 로그인 요청)
                .user(email).password("12345"))
        		.andExpect(SecurityMockMvcResultMatchers.unauthenticated()); //로그인이 실패해서 인증되면 테스트 코드를 통과시킨다.
	}

}
