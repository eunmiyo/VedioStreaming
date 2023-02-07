package com.streaming.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.streaming.dto.MainMovieDto;
import com.streaming.dto.MovieFormDto;
import com.streaming.entity.Movie;
import com.streaming.entity.MovieImg;
import com.streaming.repository.MovieImgRepository;
import com.streaming.repository.MovieRepository;

import lombok.RequiredArgsConstructor;

@Service //service 클래스의 역할, controller와 Repository사이에 있음
@Transactional //서비스 클래스에서 로직을 처리하다가 에러가 발생하면 로직을 수행하기 이전 상태로 되돌려 준다. 
@RequiredArgsConstructor
public class MovieService {
	
	private final MovieImgService movieImgService;
	
	private final MovieImgRepository movieImgRepository;
	
	//의존성 주입
	private final MovieRepository movieRepository;
	
	
	
	public Long uploadMovie(MovieFormDto movieFormDto, MultipartFile[] videoFile, MultipartFile[] imgFile) throws Exception {
		
		uploadValidation(movieFormDto, videoFile, imgFile);
		
		Movie movie = movieFormDto.createMovie(); //DTO 를 ENTITY 로 변환
		movieRepository.save(movie); //영화정보저장
		
		MovieImg movieImg = new MovieImg();
		movieImg.setMovie(movie); //ENTITY 정보를 다른 ENTITY 정보로 설정
		
		movieImgService.uploadMovieFile(movieImg, videoFile, imgFile); //파일 업로드
		
		return movie.getId();
	}
	
	private void uploadValidation(MovieFormDto moiveFormDto, MultipartFile[] videoFile, MultipartFile[] imgFile) throws Exception {
		//영화 정보 확인
		if (moiveFormDto == null) {
			throw new Exception("영화 정보가 넘어오지 않았습니다");
		} else {
			//
		}
		
		//비디오 파일 확인
		if (videoFile == null || videoFile.length == 0) {
			throw new Exception("비디오 파일이 넘어오지 않았습니다");
		} else {
			//
		}
		
		//이미지 파일 확인
		if (imgFile == null || imgFile.length == 0) {
			throw new Exception("이미지 파일이 넘어오지 않았습니다");
		} else {
			//
		}
	}
	
	@Transactional(readOnly = true)
	public Page<MainMovieDto> getMainItemPage(Pageable pageable) {
		return movieRepository.getMainItemPage(pageable);
	}
}
