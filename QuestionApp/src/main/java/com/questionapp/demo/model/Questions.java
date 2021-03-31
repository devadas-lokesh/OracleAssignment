package com.questionapp.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity(name = "questions")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})


public class Questions {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="questionid")
	private int QuestionId;
	@Column(name="questions")
	private String Questions;
	public int getQuestionId() {
		return QuestionId;
	}
	public void setQuestionId(int questionId) {
		QuestionId = questionId;
	}
	public String getQuestions() {
		return Questions;
	}
	public void setQuestions(String questions) {
		Questions = questions;
	}
	

}
