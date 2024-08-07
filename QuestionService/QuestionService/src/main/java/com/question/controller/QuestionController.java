package com.question.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.question.entity.Question;
import com.question.service.QuestionService;


@RestController
@RequestMapping("/question")
public class QuestionController {
	
	
	private QuestionService questionService;

	public QuestionController(QuestionService questionService) {
		super();
		this.questionService = questionService;
	}
	
	
	@PostMapping 
	public Question create(@RequestBody Question question) {
		return this.questionService.create(question);
	}
	
	@GetMapping
	public List<Question> getAll(){
		return this.questionService.get();
	}

	@GetMapping("/{id}")
	public Question getOne(@PathVariable Long id) {
		return this.questionService.getOne(id);
	}
	
	//get all question of specific quiz
	@GetMapping("/quiz/{quizId}")
	public List<Question>  getQuestionsOfQuiz(@PathVariable Long quizId){
		return this.questionService.getQuestionsOfQuiz(quizId);
	}
	
	
	
}
