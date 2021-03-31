package com.questionapp.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.questionapp.demo.model.Questions;
import com.questionapp.demo.model.TemplateQuestionMapping;
import com.questionapp.demo.repositories.QuestionsService;
import com.questionapp.demo.repositories.TemplateQuestionMappingService;

@RestController
@RequestMapping("/api/mapping")
public class TemplateQuestionMappingController {
	
	@Autowired
	private TemplateQuestionMappingService tqMapService;

	
	@GetMapping
	public List<TemplateQuestionMapping> getAllMapping()
	{
		return tqMapService.findAll(Sort.by(Sort.Direction.ASC, "orderid"));
	}
	
	@GetMapping("/{id}")
	public TemplateQuestionMapping getMapping(@PathVariable("id") int id)
	{
		return tqMapService.getOne(id);
		
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.OK)
	public void mapQuestion(@RequestBody TemplateQuestionMapping tqMap)
	{
		TemplateQuestionMapping tempmap = new TemplateQuestionMapping();
		tempmap.setTemplateid(tqMap.getTemplateid());
		tempmap.setQuestionid(tqMap.getQuestionid());
		tempmap.setOrderid(tqMap.getOrderid());
		tempmap.setType(tqMap.getType());
		tqMapService.save(tempmap);
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteMapping(@PathVariable int id)
	{
		tqMapService.deleteById(id);
	}
	
	@PutMapping
	public TemplateQuestionMapping updateMapping( @RequestBody TemplateQuestionMapping mapping)
	{
		
		return tqMapService.saveAndFlush(mapping);
	}
	

}
