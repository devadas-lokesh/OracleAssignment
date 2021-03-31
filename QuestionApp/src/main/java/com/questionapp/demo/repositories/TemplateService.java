package com.questionapp.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.questionapp.demo.model.Templates;

public interface TemplateService extends JpaRepository<Templates, Integer> {

}
