package com.streaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streaming.entity.MovieImg;

//DAO역할을 하는 Repository 인터페이스
//JpaRepository: 기본적인 CRUD 및 페이징 처리를 위한 메소드가 정의 되어있다.
//JpaRepository<사용할 엔티티 클래스, 기본키 타입>
public interface MovieImgRepository extends JpaRepository<MovieImg, Long>  {

}
