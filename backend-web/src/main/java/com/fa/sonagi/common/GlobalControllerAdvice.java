package com.fa.sonagi.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

// com.example 패키지 하위에 Controller의 예외를 모두 해당 클래스에서 처리
@Slf4j
@RestControllerAdvice(basePackages = "com.fa")
public class GlobalControllerAdvice {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.info("exception is found at /{} ", e.getMessage());
		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(e.getBindingResult());
	}

	// 모든 Exception 핸들러 정의
	// 500 Error Code 반환 : 서버 에러
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity exception(Exception e) {
		log.info("exception is found at /{} ", e.getMessage());
		return ResponseEntity
			.status(HttpStatus.OK)
			.body("exception is found at /{} " + e.getMessage());
	}

}
