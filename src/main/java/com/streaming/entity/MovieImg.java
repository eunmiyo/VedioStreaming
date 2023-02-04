package com.streaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="movie_img")
@Getter
@Setter
public class MovieImg extends BaseEntity {
	@Id
	@Column(name="movie_img_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String imgName; //이미지 파일명
	
	private String oriImgName; //원본 이미지 파일명
	
	private String imgUrl; //이미지 조회 경로
	
	private String videoName;
	
	private String orivideoName;
	
	private String videoUrl;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	//원본이미지 파일명, 업데이트 할 이미지 파일명, 이미지 경로를 파라메타로 받아서 이미지 정보를 업데이트하는 메소드
	public void updateItemImg(String oriImgName, String imgName, String imgUrl) {
		this.oriImgName = oriImgName;
		this.imgName = imgName;
		this.imgUrl = imgUrl;
	}
}
