package com.streaming.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.streaming.dto.MainMovieDto;
import com.streaming.service.MovieService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MainController {
	private final MovieService movieService;
	
	@GetMapping(value="/")
	public String main(Optional<Integer> page, Model model) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
		Page<MainMovieDto> movies = movieService.getMainItemPage(pageable);
		
		model.addAttribute("movies", movies);
		model.addAttribute("maxPage", 5);
		
		return "main";
	}
}
