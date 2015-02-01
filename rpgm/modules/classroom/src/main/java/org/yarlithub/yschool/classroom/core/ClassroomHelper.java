package org.yarlithub.yschool.classroom.core;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yarlithub.yschool.repository.model.obj.yschool.Classroom;
import org.yarlithub.yschool.repository.model.obj.yschool.Grade;
import org.yarlithub.yschool.repository.services.data.DataLayerYschool;
import org.yarlithub.yschool.repository.services.data.DataLayerYschoolImpl;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jay Krish
 * Date: 10/25/13
 * Time: 12:20 AM
 * To change this template use File | Settings | File Templates.
 * Updated by Phool Chandra
 */


public class ClassroomHelper {
	private static final Logger logger = LoggerFactory.getLogger(ClassroomHelper.class);
    static DataLayerYschool dataLayerYschool = DataLayerYschoolImpl.getInstance();

    /**
     * Returns Classroom objects for the current year for the given grade.
     *
     * @param grade org.yarlithub.yschool.repository.model.obj.yschool.Grade
     * @param year  int year.
     * @return List of org.yarlithub.yschool.repository.model.obj.yschool.Classroom,
     * empty list if no matching occurred.
     */
    public static List<Classroom> getClassrooms(Grade grade, int year) {
    	logger.debug("with in Helper method...");
        Criteria classroomCriteria = dataLayerYschool.createCriteria(Classroom.class);
        classroomCriteria.add(Restrictions.eq("year", year));
        classroomCriteria.add(Restrictions.eq("gradeIdgrade", grade));
        return classroomCriteria.list();
    }
    public static String getStaffNameByClassId(Classroom classroom){
    	return null;
    }
}
