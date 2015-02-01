package org.yarlithub.yschool.web.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.yarlithub.yschool.repository.model.obj.yschool.Student;
import org.yarlithub.yschool.service.StudentService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

/**
 * $LastChangedDate$
 * $LastChangedBy$
 * $LastChangedRevision$
 */
@ManagedBean
@Scope(value = "view")
@Controller
public class StudentSearchBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3918075112764680146L;
	private Student student;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentController studentController;

    @PostConstruct
    public void init() {
        setStudent(studentController.getStudent());
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String backToHome() {
        return "backStudentHome";
    }
}

