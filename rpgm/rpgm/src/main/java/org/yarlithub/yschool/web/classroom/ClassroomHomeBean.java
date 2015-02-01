package org.yarlithub.yschool.web.classroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.yarlithub.yschool.repository.model.obj.yschool.Classroom;
import org.yarlithub.yschool.repository.model.obj.yschool.Grade;
import org.yarlithub.yschool.service.ClassroomService;
import org.yarlithub.yschool.web.commons.Properties;



import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: Jay Krish
 * Date: 12/19/13
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@Scope(value = "view")
@Controller
public class ClassroomHomeBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4427099701759209287L;
	private static final Logger logger = LoggerFactory.getLogger(ClassroomHomeBean.class);
	@Autowired
    private ClassroomService classroomService;
    private DataModel<GradeView> gradeDataModel;

    @PostConstruct
    public void init() {
    	logger.debug("with in init method of ClassroomHomeBean..........");
        List<Grade> gradeList = classroomService.getGrades();
        Iterator gradeListIterator = gradeList.iterator();
        List<GradeView> gradeViewList = new ArrayList<GradeView>();
        while (gradeListIterator.hasNext()) {
            Grade grade = (Grade) gradeListIterator.next();
            logger.debug("Properties.getCurrentYear()------"+Properties.getCurrentYear());
            DataModel<Classroom> classrooms = new ListDataModel<Classroom>(classroomService.getClassrooms(grade, Properties.getCurrentYear()));
            GradeView gradeView = new GradeView(classrooms, grade);
            gradeViewList.add(gradeView);
        }
        gradeDataModel = new ListDataModel<GradeView>(gradeViewList);
    }

    public DataModel<GradeView> getGradeDataModel() {
        return gradeDataModel;
    }

    public void setGradeDataModel(DataModel<GradeView> gradeDataModel) {
        this.gradeDataModel = gradeDataModel;
    }

    public String editGrade() {
        return "editGrade";
    }

    public String viewClassroom() {
        return "viewClassroom";
    }

}
