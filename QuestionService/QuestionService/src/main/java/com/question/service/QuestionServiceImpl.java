package com.question.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.question.entity.Question;
import com.question.repository.QuestionRepository;

@Service
public class QuestionServiceImpl  implements QuestionService{

	private QuestionRepository questionRepository;
	
	
	
	
	public QuestionServiceImpl(QuestionRepository questionRepository) {
		super();
		this.questionRepository = questionRepository;
	}

	@Override
	public Question create(Question question) {
		return this.questionRepository.save(question);
	}

	@Override
	public List<Question> get() {
		return this.questionRepository.findAll();
	}

	@Override
	public Question getOne(Long id) {
		return this.questionRepository.findById(id).orElseThrow(()-> new RuntimeException("No question found"));
	}

	@Override
	public List<Question> getQuestionsOfQuiz(Long quizId) {
		return this.questionRepository.findByQuizId(quizId);
	}

}
