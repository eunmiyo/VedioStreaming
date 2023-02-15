package com.streaming.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.streaming.dto.MainMovieDto;
import com.streaming.dto.QMainMovieDto;
import com.streaming.entity.QMovie;
import com.streaming.entity.QMovieImg;

public class MovieRepositoryCustomImpl implements MovieRepositoryCustom {
	private JPAQueryFactory queryFactory;
	
	public MovieRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public Page<MainMovieDto> getMainItemPage(Pageable pageable) {
		QMovie movie = QMovie.movie;
		QMovieImg movieImg = QMovieImg.movieImg;
		
		List<MainMovieDto> content = queryFactory.select(
									new QMainMovieDto(movie.id, 
													movie.title, 
													movie.category, 
													movie.openDate, 
													movie.story, 
													movieImg.imgUrl,
													movieImg.videoUrl))
									.from(movieImg)
									.join(movieImg.movie, movie)
									.orderBy(movie.id.desc())
									.offset(pageable.getOffset())
									.limit(pageable.getPageSize())
									.fetch();
		long total = queryFactory
					.select(Wildcard.count)
					.from(movieImg)
					.join(movieImg.movie, movie)
					.fetchOne();
		
		return new PageImpl<>(content,pageable,total);
	}
	
	
	
}
