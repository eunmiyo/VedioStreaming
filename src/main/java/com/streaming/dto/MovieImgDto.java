package com.streaming.dto;

import org.modelmapper.ModelMapper;

import com.streaming.entity.MovieImg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieImgDto {
	
	private Long id;
	
	private String imgName; //이미지 파일명
	
	private String oriImgName; //원본 이미지 파일명
	
	private String imgUrl; //이미지 조회 경로
	
	private String videoUrl; //동영상
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static MovieImgDto of(MovieImg movieImg) {
		return modelMapper.map(movieImg, MovieImgDto.class);
	}
}
