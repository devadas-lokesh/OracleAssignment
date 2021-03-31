package com.questionapp.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.questionapp.demo.repositories.QuestionsService;
import com.questionapp.demo.model.Questions;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {
	
	@Autowired
	private QuestionsService questionRepo;
	
	
	
	@GetMapping
	public List<Questions> getAllQuestions()
	{
		
		 
		return questionRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Questions getQuestion(@PathVariable("id") int id)
	{
		
		return questionRepo.getOne(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public Questions AddQuestion(@RequestBody String question)
	{
		Questions qTemp=new Questions();
		System.out.println(question);
		qTemp.setQuestions(question.substring(1, question.length()-1));
		
		return questionRepo.save(qTemp);
		
	}		
		
	
	

}



