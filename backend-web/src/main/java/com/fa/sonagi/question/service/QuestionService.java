package com.fa.sonagi.question.service;

import java.util.List;

import com.fa.sonagi.question.dto.QuestionPostDto;
import com.fa.sonagi.question.dto.QuestionResDto;

public interface QuestionService {
	QuestionResDto findQuestionById(Long questionId);

	void registQuestion(QuestionPostDto questionPostDto);

	List<QuestionResDto> findQuestionList();
}
