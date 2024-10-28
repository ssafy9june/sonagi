package com.fa.sonagi.question.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.question.dto.QuestionPostDto;
import com.fa.sonagi.question.dto.QuestionResDto;
import com.fa.sonagi.question.entity.Question;
import com.fa.sonagi.question.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
	private final QuestionRepository questionRepository;

	/**
	 * 문의사항 조회
	 */
	@Override
	public QuestionResDto findQuestionById(Long questionID) {
		Question question = questionRepository.findById(questionID).orElseThrow();

		QuestionResDto questionResDto = QuestionResDto.builder()
			.questionId(question.getQuestionID())
			.userId(question.getUserId())
			.title(question.getTitle())
			.content(question.getContent())
			.createdAt(question.getCreatedAt())
			.build();
		return questionResDto;
	}

	/**
	 * 문의사항 등록
	 */
	@Override
	@Transactional
	public void registQuestion(QuestionPostDto questionPostDto) {
		Question question = Question.builder()
			.userId(questionPostDto.getUserId())
			.title(questionPostDto.getTitle())
			.content(questionPostDto.getContent())
			.createdAt(questionPostDto.getCreatedAt())
			.build();
		questionRepository.save(question);
	}

	/**
	 * 문의사항 리스트 조회
	 */
	@Override
	public List<QuestionResDto> findQuestionList() {
		List<Question> questions = questionRepository.findAll();

		return questions.stream()
			.map(q -> QuestionResDto.builder()
				.questionId(q.getQuestionID())
				.title(q.getTitle())
				.userId(q.getUserId())
				.createdAt(q.getCreatedAt())
				.content(q.getContent())
				.build())
			.collect(Collectors.toList());
	}
}
