package org.yarlithub.yschool.web.student;

import org.apache.commons.io.IOUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.yarlithub.yschool.repository.model.obj.yschool.Student;
import org.yarlithub.yschool.service.StudentService;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;


/**
 * $LastChangedDate$
 * $LastChangedBy$
 * $LastChangedRevision$
 */
@ManagedBean
@Scope(value = "view")
@Controller
public class StudentNewBean implements Serializable {

    private String admission_No;
    private String name;
    private String fullname;
    private String name_wt_initial;
    private Date dob;
    private String gender;
   	private UploadedFile photo;
    private String address;
    @Autowired
    private StudentService studentService;
   

    public String getAdmission_No() {
        return admission_No;
    }

    public void setAdmission_No(String admission_No) {
        this.admission_No = admission_No;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getName_wt_initial() {
        return name_wt_initial;
    }

    public void setName_wt_initial(String name_wt_initial) {
        this.name_wt_initial = name_wt_initial;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public UploadedFile getPhoto() {
		return photo;
	}

	public void setPhoto(UploadedFile photo) {
		this.photo = photo;
	}


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String addStudent() {
    	byte[] bytesofimage=new byte[6144];
		try {
			bytesofimage = photo.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//byte[] bytesofimage=new byte[(int)photo.getSize()];		
       
        Student student = studentService.addNewStudent(admission_No, name, fullname, name_wt_initial, dob, gender, address,bytesofimage);
        if (student.getId() > 0) {
            return "AddStudentSuccess";
        }
        return "AddStudentFailed";
    }
   
}
