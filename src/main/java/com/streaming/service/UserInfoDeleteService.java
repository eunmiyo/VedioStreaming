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

@Service
@Transactional
@RequiredArgsConstructor
public class UserInfoDeleteService implements UserDetailsService {
	private final UserInfoRepository userInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserInfo userInfo = userInfoRepository.findByEmail(email);
		
		if(userInfo == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return User.builder()
				.username(userInfo.getEmail())
				.password(userInfo.getPassword())
				.roles(userInfo.getRole().toString())
				.build();
	}
	
}
