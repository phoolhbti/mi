package org.yarlithub.yschool.web.student;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.yarlithub.yschool.repository.model.obj.yschool.Classroom;
import org.yarlithub.yschool.repository.model.obj.yschool.Student;
import org.yarlithub.yschool.service.StudentService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import java.io.Serializable;


/**
 * $LastChangedDate$
 * $LastChangedBy$
 * $LastChangedRevision$
 */
@ManagedBean
@Scope(value = "view")
@Controller
public class StudentListBean implements Serializable {
	private static final Logger logger = Logger.getLogger(StudentListBean.class);

    private DataModel<Student> listStudents;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentController studentController;

    @PostConstruct
    public void init() {
        Classroom classroom = studentController.getClassroom();
        listStudents = studentController.getStudentList();
    }

    public DataModel getListStudents() {
        return listStudents;
    }

    public void setListStudents(DataModel listStudents) {
        this.listStudents = listStudents;
    }

    public String viewStudent() {
        Student student = (Student) listStudents.getRowData();
        studentController.setStudent(student);
        logger.error("This is Error message stall student======="+student.getName());
        return "viewStudent";
    }
}
