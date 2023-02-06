package com.streaming.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.streaming.dto.MovieFormDto;
import com.streaming.service.MovieService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UploadContoller {
	
	private final MovieService movieService;
	
	@GetMapping(value = "admin/upload")
	public String upload(Model model) {
		model.addAttribute("movieFormDto", new MovieFormDto());
		return "movie/movieForm";
	}

	@PostMapping(value = "admin/upload")
	public String uploadFormPost(@Valid MovieFormDto moiveFormDto, BindingResult bindingResult, 
			Model model, @RequestParam("videoFile") MultipartFile[] videoFile, @RequestParam("imgFile") MultipartFile[] imgFile) {
		try {
			//서비스 호출
			movieService.uploadMovie(moiveFormDto, videoFile, imgFile);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "영화 저장에 실패하였습니다");
		}
		
		//forward
		return "/movie/movieForm";
	}
}
