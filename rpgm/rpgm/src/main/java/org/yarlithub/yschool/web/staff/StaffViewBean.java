package org.yarlithub.yschool.web.staff;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.yarlithub.yschool.repository.model.obj.yschool.Staff;
import org.yarlithub.yschool.service.StaffService;

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
public class StaffViewBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6470865549909815272L;

	private  Staff staff;

    @Autowired
    private StaffService staffService;
    @Autowired
    private StaffController staffController;
    private StreamedContent dbImage;

    @PostConstruct
    public void init() {
        setStaff(staffController.getStaff());
    }
    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public  boolean preLoad(){
        setStaff(staffController.getStaff());
        return  true;
    }
    public String editStaff() {
        staffController.setStaff(staff);
        return "EditStaff";
    }
    public String backToHomeStaff() {
        staffController.setStaff(staff);
        return "BackToHomeStaff";
    }
   public String staffDelete() {
        staffService.deleteStaff(staff);
        return "staffHome";
    }
    public StreamedContent getDbImage() {
    	InputStream dbStream=null;
    	
		try{
		if(null!=staff.getPhoto()){
		dbStream = new ByteArrayInputStream(staff.getPhoto());
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
