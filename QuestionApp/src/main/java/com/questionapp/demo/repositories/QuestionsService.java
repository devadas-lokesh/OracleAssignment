package com.questionapp.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.questionapp.demo.model.Questions;


public interface QuestionsService extends JpaRepository<Questions, Integer>{

}
