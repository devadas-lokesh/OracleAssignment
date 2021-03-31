package com.questionapp.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.questionapp.demo.model.TemplateQuestionMapping;
import com.questionapp.demo.model.Templates;
import com.questionapp.demo.repositories.TemplateQuestionMappingService;
import com.questionapp.demo.repositories.TemplateService;

@RestController
@RequestMapping("/api/templates")
public class TemplateController {
	
	@Autowired
	private TemplateService tService;
	
	@Autowired
	private TemplateQuestionMappingService tqMapping;
	
	
	@GetMapping
	public List<Templates> getTemplates()
	{
		return tService.findAll(Sort.by(Sort.Direction.ASC, "templateid"));
	}
	
	@GetMapping("/{id}")
	public Templates getTemplate(@PathVariable("id") int id) 
	{
		return tService.getOne(id);
	}
	
	@PostMapping
	public Templates AddTemplate(@RequestBody Templates template)
	{
		Templates temp=new Templates();
		List<TemplateQuestionMapping> tempmap = tqMapping.findAll();
		System.out.println(temp.getTemplateid());
		temp.setTemplatename("Copy of "+template.getTemplatename());
		temp.setTemplateactive(false);
		temp = tService.saveAndFlush(temp);
		for(TemplateQuestionMapping map:tempmap)
		{
			if(map.getTemplateid()==template.getTemplateid())
			{
				TemplateQuestionMapping mapping =new TemplateQuestionMapping();
				mapping.setTemplateid(temp.getTemplateid());
				mapping.setQuestionid(map.getQuestionid());
				mapping.setOrderid(map.getOrderid());
				mapping.setType(map.getType());
				tqMapping.save(mapping);
			}
		}
		
		return temp;
	}
	
	@PutMapping("/{id}")
	public void updateMapping(@PathVariable int id, @RequestBody Templates template)
	{
		Templates existingMapping = tService.getOne(id);
		existingMapping.setTemplateactive(false);
		tService.saveAndFlush(existingMapping);
		existingMapping = tService.getOne(template.getTemplateid());
		existingMapping.setTemplateactive(true);
		tService.saveAndFlush(existingMapping);
		
	}
	
	

}
