package org.yarlithub.yschool.web.staff;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.yarlithub.yschool.repository.model.obj.yschool.Staff;
import org.yarlithub.yschool.service.StaffService;

import javax.faces.bean.ManagedBean;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;




/**
 * $LastChangedDate$
 * $LastChangedBy$
 * $LastChangedRevision$
 */

@ManagedBean
@Scope(value = "view")
@Controller
public class StaffSearchBean implements Serializable {


    private Staff staff;
    @Autowired
    private StaffService staffService;
    @Autowired
    private StaffController staffController;
    private String ImageName;
    private StreamedContent dbImage;
    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    public boolean preLoad() {

        setStaff(staffController.getStaff());
        return  true;
    }
    public String getImageName(){
    	//ByteArrayInputStream barryis=new ByteArrayInputStream(staff.getPhoto());
    	String imageNameLoc="D:/Projects/images/"+staff.getName()+".jpg";
    	InputStream in = new ByteArrayInputStream(staff.getPhoto());
    	try {
			BufferedImage bImageFromConvert = ImageIO.read(in);
			ImageIO.write(bImageFromConvert, "jpg", new File(imageNameLoc));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return imageNameLoc;
    }
    public void setImageName(String imageName){
    	this.ImageName=imageName;
    }

	public StreamedContent getDbImage() {
		InputStream dbStream = new ByteArrayInputStream(staff.getPhoto());
		dbImage = new DefaultStreamedContent(dbStream,"image/jpeg");
		return dbImage;
	}

	public void setDbImage(StreamedContent dbImage) {
		this.dbImage = dbImage;
	}
}
