package com.questionapp.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "templates")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

public class Templates {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int templateid;
	public Templates() {
		
	}
	private String templatename;
	private boolean templateactive;
	public int getTemplateid() {
		return templateid;
	}
	public void setTemplateid(int templateid) {
		this.templateid = templateid;
	}
	public String getTemplatename() {
		return templatename;
	}
	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}
	public boolean isTemplateactive() {
		return templateactive;
	}
	public void setTemplateactive(boolean templateactive) {
		this.templateactive = templateactive;
	}

}
