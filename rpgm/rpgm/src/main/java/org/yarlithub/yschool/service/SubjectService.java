package org.yarlithub.yschool.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.yarlithub.yschool.repository.model.obj.yschool.Subject;

import com.rpgm.online.subject.core.SubjectCreator;
import com.rpgm.online.subject.core.SubjectHelper;

@Service(value = "subjectService")
public class SubjectService {
	 private static final Logger logger = LoggerFactory.getLogger(SubjectService.class);
	 @Transactional
	 public Subject addSubject(String subject_code,String subject_name){
		 SubjectCreator subjectCreator=new SubjectCreator();
		 Subject subject=subjectCreator.addNewSubject(subject_code,subject_name);
		 return subject;
		 
	 }
	 @Transactional
	    public List<Subject> getAllSubjects() {
		 SubjectHelper subjectHelper=new SubjectHelper();
	        List<Subject> allSubjects = subjectHelper.listAllSubjects();
	        for (Subject availableSubject : allSubjects) {
	            Hibernate.initialize(availableSubject.getSubjectCode());
	            Hibernate.initialize(availableSubject.getSubjectName());
	        }
	        return allSubjects;
	    }
}
