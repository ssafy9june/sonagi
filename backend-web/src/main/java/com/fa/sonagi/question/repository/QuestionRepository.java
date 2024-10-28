package com.fa.sonagi.question.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.question.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
