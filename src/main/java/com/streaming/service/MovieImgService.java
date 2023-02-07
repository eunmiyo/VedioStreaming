package com.streaming.service;

import java.io.File;

import org.apache.groovy.parser.antlr4.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.streaming.entity.MovieImg;
import com.streaming.repository.MovieImgRepository;

import lombok.RequiredArgsConstructor;

@Service //service 클래스의 역할, controller와 Repository사이에 있음
@Transactional //서비스 클래스에서 로직을 처리하다가 에러가 발생하면 로직을 수행하기 이전 상태로 되돌려 준다. 
@RequiredArgsConstructor
public class MovieImgService {
	
	@Value("${movieImgLocation}")
	private String movieImgLocation;

	//의존성 주입
	private final MovieImgRepository movieImgRepository;
	
	public void uploadMovieFile(MovieImg movieImg, MultipartFile[] videoFile, MultipartFile[] imgFile) throws Exception {
		
		uploadValidation(movieImg, videoFile, imgFile);
		
		//전달된 비디오 파일이 없는 경우
		String videoFileName = uploadFile(movieImgLocation, videoFile);
		
		//전달된 이미지 파일이 없는 경우
		String imgFileName = uploadFile(movieImgLocation, imgFile);
		
		movieImg.updateMovieVideo(videoFileName, videoFileName, "/images/"+videoFileName);
		movieImg.updateMovieImg(imgFileName, imgFileName, "/images/"+imgFileName);
		
		movieImgRepository.save(movieImg);
	}
	
	private void uploadValidation(MovieImg movieImg, MultipartFile[] videoFile, MultipartFile[] imgFile) throws Exception {
		//영화 정보 확인
		if (movieImg == null) {
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
	
	private String uploadFile(String movieImgLocation, MultipartFile[] uploadFile) throws Exception {
		String originalFilename = "";
		
		for(MultipartFile multipartFile : uploadFile) {
			originalFilename = multipartFile.getOriginalFilename();
			
			System.out.println("-----------");
			//클라이언트서버에 저장된 파일명
			System.out.println("파일명 : " + originalFilename);
			System.out.println("파일크기 : " + multipartFile.getSize());
			
			// uploadFolder\\gongu03.jpg으로 조립
			// 이렇게 업로드 하겠다라고 설계
			// 서버에 저장하면서 서버에 저장될 이름 설정
			File saveFile = new File(movieImgLocation, originalFilename);
			
			try {
				//transferTo() : 물리적으로 파일 업로드가 됨
				multipartFile.transferTo(saveFile);
			} catch(Exception e) {
				System.out.println(e.getMessage());
				throw e;
			}//end catch
		}//end for
		
		return originalFilename;
	}
	
}
