package com.fa.sonagi.diary.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3File {

	String upload(MultipartFile profile, String dirName) throws Exception;

	boolean delete(String filePath);
}