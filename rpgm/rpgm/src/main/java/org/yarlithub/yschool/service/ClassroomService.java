package org.yarlithub.yschool.service;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yarlithub.yschool.classroom.core.ClassroomHelper;
import org.yarlithub.yschool.commons.api.CommonsHelper;
import org.yarlithub.yschool.repository.model.obj.yschool.Classroom;
import org.yarlithub.yschool.repository.model.obj.yschool.Grade;

import java.util.Iterator;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: Jay Krish
 * Date: 9/22/13
 * Time: 9:05 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * TODO description
 */
@Service(value = "classroomService")
public class ClassroomService {
    private static final Logger logger = LoggerFactory.getLogger(ClassroomService.class);

    @Transactional
    public List<Classroom> getClassrooms(Grade grade, int year) {
    	logger.debug("int in year...."+year);
        ClassroomHelper classroomHelper = new ClassroomHelper();
        List<Classroom> classroomList = classroomHelper.getClassrooms(grade, year);
        Iterator<Classroom> classroomIterator = classroomList.iterator();
        while (classroomIterator.hasNext()) {
            Classroom classroom = classroomIterator.next();
            Hibernate.initialize(classroom.getGradeIdgrade());
            Hibernate.initialize(classroom.getDivisionIddivision());
        }
        return classroomList;
    }
    /*@Transactional
    public Classroom getClassroom(int classroomid) {
    	logger.debug("int in year...."+classroomid);
        ClassroomHelper classroomHelper = new ClassroomHelper();
    
               return null;
    }*/
    @Transactional
    public List<Grade> getGrades() {
        return CommonsHelper.getAllGrades();
    }
}
