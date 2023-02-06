package com.streaming.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.streaming.dto.MainMovieDto;

public interface MovieRepositoryCustom {
	Page<MainMovieDto> getMainItemPage(Pageable pageable);
}
