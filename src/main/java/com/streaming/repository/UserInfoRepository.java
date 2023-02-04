package com.streaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streaming.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	//회원가입시 중복 회원이 있는지 검사하기 위해
	UserInfo findByEmail(String Email);
}
