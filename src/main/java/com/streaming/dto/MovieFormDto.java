package com.streaming.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;

import com.streaming.entity.Movie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieFormDto {
	
	private Long id; //영화코드
	
	@NotBlank(message = "영화제목은 필수 입력 값입니다.")
	private String title; //영화제목
	
	@NotBlank(message = "영화장르는 필수 입력 값입니다.")
	private String category; //장르
	
	@NotBlank(message = "영화줄거리는 필수 입력 값입니다.")
	private String story; //줄거리
	
	@NotBlank(message = "영화개봉날짜는 필수 입력 값입니다.")
	private String openDate; //개봉날짜
	
	private List<MovieImgDto> movieImgDtoList = new ArrayList();
	
	private List<Long> movieImgIds = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Movie createMovie() {
		return modelMapper.map(this, Movie.class);
	}
	
	public static MovieFormDto of(Movie movie) {
		return modelMapper.map(movie, MovieFormDto.class);
	}
	
	
	
}
