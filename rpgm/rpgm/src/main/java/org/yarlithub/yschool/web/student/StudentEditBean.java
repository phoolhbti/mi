package org.yarlithub.yschool.web.student;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.yarlithub.yschool.repository.model.obj.yschool.Student;
import org.yarlithub.yschool.service.StudentService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;

/**
 * $LastChangedDate$
 * $LastChangedBy$
 * $LastChangedRevision$
 */
@ManagedBean
@Scope(value = "request")
@Controller
public class StudentEditBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6485528196809974974L;
	private Student student;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentController studentController;
    private StreamedContent dbImage;

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

    public String editstudent() {
        studentService.saveOrUpdate(student);
        return "editStudent";
    }

    public String backToView() {
        return "backToView";
    }
    public StreamedContent getDbImage() {
    	InputStream dbStream=null;
    	
		try{
		if(null!=student.getPhoto()){
		dbStream = new ByteArrayInputStream(student.getPhoto());
		dbImage = new DefaultStreamedContent(dbStream,"image/jpeg");		
		return dbImage;
			}
		else
		{
			dbStream = new URL("/yschool/ico/student_icon.png").openStream();
			dbImage = new DefaultStreamedContent(dbStream,"image/png");
			return dbImage;
		}
		} catch (IOException e) {
        	e.printStackTrace();
        }
		return null;
	}

	public void setDbImage(StreamedContent dbImage) {
		this.dbImage = dbImage;
	}
}

