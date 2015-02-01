package com.rpgm.online.subject;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import org.yarlithub.yschool.repository.model.obj.yschool.Subject;
import org.yarlithub.yschool.service.SubjectService;

@ManagedBean
@Scope(value = "request")
@Controller
public class SubjectBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -822211271740843451L;
	private String name;
	private String code;
	 private List<Subject> availableSubjects;
	 @Autowired
	 private SubjectService subjectService;
	
	 @PostConstruct
	    public void init() {
		 setAvailableSubjects(this.subjectService.getAllSubjects());
	 }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<Subject> getAvailableSubjects() {
		return availableSubjects;
	}
	public void setAvailableSubjects(List<Subject> availableSubjects) {
		this.availableSubjects = availableSubjects;
	}
	
}
