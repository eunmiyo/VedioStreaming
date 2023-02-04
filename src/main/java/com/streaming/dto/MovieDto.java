package com.streaming.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {
	private Long id; //영화코드
	
	private String title; //영화제목
	
	private String category; //장르
	
	private String story; //줄거리
	
	private String openDate; //개봉날짜
	
	private LocalDateTime regTime; //등록 시간
	
	private LocalDateTime updateTime; //수정 시간
}