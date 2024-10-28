package com.fa.sonagi.diary.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

class DiaryControllerTest {

	@DisplayName("Post/ 파일 등록 api 테스트")
	@Test
	void registDiaries() {
		//given
		MockMultipartFile multipartFile1 = new MockMultipartFile("file", "test.txt", "text/plain", "test file".getBytes(StandardCharsets.UTF_8));
		MockMultipartFile multipartFile2 = new MockMultipartFile("file2", "test2.txt", "text/plain", "test file".getBytes(StandardCharsets.UTF_8));


	}
}