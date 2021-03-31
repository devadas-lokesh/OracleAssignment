package com.questionapp.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.questionapp.demo.model.TemplateQuestionMapping;

public interface TemplateQuestionMappingService extends JpaRepository<TemplateQuestionMapping, Integer> {
	


}
