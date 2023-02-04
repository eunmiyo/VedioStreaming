package com.streaming.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSearchDto {
	private String searchDateType;
	private String searchBy;
	private String searchQuery ="";
}
