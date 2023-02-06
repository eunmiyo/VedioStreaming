package com.streaming.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainMovieDto {
	private Long id;
	private String title;
	private String category;
	private String openDate;
	private String story;
	private String imgUrl;
	
	@QueryProjection
	public MainMovieDto(Long id, String title, String category, String openDate, String story, String imgUrl) {
		this.id = id;
		this.title = title;
		this.category = category;
		this.openDate = openDate;
		this.story = story;
		this.imgUrl = imgUrl;
	}
}
