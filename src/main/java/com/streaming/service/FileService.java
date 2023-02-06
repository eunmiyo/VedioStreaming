package com.streaming.service;

import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service
@Log
public class FileService {
	//파일 업로드
	public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
		UUID uuid = UUID.randomUUID(); //중복되지 않은 랜덤한 파일이름 생성
		
		//확장자명 
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		//파일이름 생성
		String savedFileName = uuid.toString() + extension;
		
		String fileUploadFullUrl = uploadPath + "/" + savedFileName;
		
		//파일 업로드
		//생성자에 파일이 저장될 위치와 파일의 이름을 같이 넘겨 출력스트림을 만든다.
		FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);	
		//출력 스트림에 파일 데이터 입력
		fos.write(fileData);
		fos.close();
		
		return savedFileName;
	}
}