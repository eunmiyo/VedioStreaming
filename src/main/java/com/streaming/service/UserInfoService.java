package com.streaming.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.streaming.entity.UserInfo;
import com.streaming.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service //service 클래스의 역할, controller와 Repository사이에 있음
@Transactional //서비스 클래스에서 로직을 처리하다가 에러가 발생하면 로직을 수행하기 이전 상태로 되돌려 준다. 
@RequiredArgsConstructor
//UserDetailsService: 로그인시 request에서 넘어온 사용자 정보를 받음
public class UserInfoService implements UserDetailsService {
	
	//의존성 주입
	private final UserInfoRepository userInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserInfo userInfo = userInfoRepository.findByEmail(email);
		
		if(userInfo == null) {
			throw new UsernameNotFoundException(email);
		}
		
		//UserDetail을 인터페이스로 상속받는 User객체에 DB에서 받은 사용자 정보를 반환한다.				
		return User.builder()
					.username(userInfo.getEmail())
					.password(userInfo.getPassword())
					.roles(userInfo.getRole().toString())
					.build();
	}
	
	public UserInfo saveUser(UserInfo userInfo) {
		vaildateDuplicateUser(userInfo);
		return userInfoRepository.save(userInfo);
	}
	
	//이메일 중복체크 메소드(이메일로 아이디를 만드는 사이트
	private void vaildateDuplicateUser(UserInfo userInfo) {
		UserInfo findUser = userInfoRepository.findByEmail(userInfo.getEmail());
		
		if(findUser != null) {
			throw new IllegalStateException("이미 가입된 회원입니다!");
		}
	}
	
	public int deleteUser(String email, String encPassword) {
		//if (userInfoRepository.findUser(email, passwordEncoder.encode(password)) == 0) {
		if (userInfoRepository.findUser(email, encPassword) == 0) {
			return -1;
		}
		
		return userInfoRepository.deleteByEmail(email);
	}
}
