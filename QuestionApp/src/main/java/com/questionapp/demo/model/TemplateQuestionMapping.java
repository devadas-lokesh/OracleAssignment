package com.questionapp.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "templatequestionmapping")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class TemplateQuestionMapping {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int mapid;
	private int templateid;
	private int questionid;
	private int orderid;
	private String type;
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getMapid() {
		return mapid;
	}
	public void setMapid(int mapid) {
		this.mapid = mapid;
	}
	public int getTemplateid() {
		return templateid;
	}
	public void setTemplateid(int templateid) {
		this.templateid = templateid;
	}
	public int getQuestionid() {
		return questionid;
	}
	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}
	

}
