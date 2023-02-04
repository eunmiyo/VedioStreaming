package com.streaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.streaming.dto.MovieFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity //클래스를 엔티티로 선언
@Table(name="movie") //Table: 엔티티와 매핑할 테이블을 지정, (name="item"): 테이블명 설정, 설정을 따로 안하면 클래스명으로 설정됨
@Getter
@Setter
@ToString
public class Movie extends BaseEntity {
	
	@Id
	@Column(name="movie_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //영화코드
	
	@Column(nullable=false)
	private String title; //영화제목
	
	@Column(nullable=false)
	private String category; //장르
	
	@Lob //대형데이터 저장
	@Column(nullable=false)
	private String story; //줄거리
	
	@Column(nullable=false)
	private String openDate; //개봉날짜
	
	public void updateMovie(MovieFormDto movieFormDto ) {
		this.title = movieFormDto.getTitle();
		this.category = movieFormDto.getCategory();
		this.openDate = movieFormDto.getOpenDate();
		this.story = movieFormDto.getStory();
		
	}
	
}
