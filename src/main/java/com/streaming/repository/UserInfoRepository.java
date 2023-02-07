package com.streaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.streaming.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	//회원가입시 중복 회원이 있는지 검사하기 위해
	UserInfo findByEmail(String email);
	
	//nativeQuery=true : DB 에서 사용하는 쿼리를 그대로 사용할 지 여부
	@Query(value="select count(*) from userinfo where email = :email and user_pw = :user_pw", nativeQuery=true)
	int findUser(@Param("email") String email, @Param("user_pw") String password);
	
	//회원삭제
	int deleteByEmail(String email);
}
