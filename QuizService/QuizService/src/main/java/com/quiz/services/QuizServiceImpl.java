package com.quiz.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.quiz.entity.Quiz;
import com.quiz.repository.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService {

	private QuizRepository quizRepository;

	private QuestionClient client;

	public QuizServiceImpl(QuizRepository quizRepository, QuestionClient client) {
		super();
		this.quizRepository = quizRepository;
		this.client = client;
	}

	@Override
	public Quiz add(Quiz quiz) {
		Quiz savedQuiz = this.quizRepository.save(quiz);

		return savedQuiz;
	}

	@Override
	public List<Quiz> get() {
		List<Quiz> quizzes = this.quizRepository.findAll();
		List <Quiz> newQuizList = quizzes.stream().map((quiz)-> 
		{
			quiz.setQuestions(this.client.getQuestionOfQuiz(quiz.getId()));
			return quiz;
		}).collect(Collectors.toList())	;
            
		
		return newQuizList;
	}

	@Override
	public Quiz get(Long id) {
		Quiz quiz = this.quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found....!"));
		
		quiz.setQuestions(this.client.getQuestionOfQuiz(quiz.getId()));
		
		return quiz;
	}

}
