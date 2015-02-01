package org.yarlithub.yschool.service;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yarlithub.yschool.repository.model.obj.yschool.Staff;
import org.yarlithub.yschool.repository.model.obj.yschool.Student;
import org.yarlithub.yschool.staff.core.StaffCreator;
import org.yarlithub.yschool.staff.core.StaffHelper;
import org.yarlithub.yschool.student.core.StudentHelper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Created with IntelliJ IDEA.
 * User: Jay Krish
 * Date: 9/22/13
 * Time: 9:05 AM
 * To change this template use File | Settings | File Templates.
 */


@Service(value = "staffService")
public class StaffService {
    private static final Logger logger = LoggerFactory.getLogger(StaffService.class);

    @Transactional
    public Staff addStaff(String staffID, String name, String fullname,byte photo[]) {
        StaffCreator staffCreator = new StaffCreator();
        /*byte[] bytesofimage=new byte[2014];
		try {
			bytesofimage = photo.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Byte[] byteObjects = new Byte[bytesofimage.length];
        */
        Staff returendStaff = staffCreator.addNewStaff(staffID, name, fullname,photo);
        return returendStaff;
        //StaffCreator staffCreator=new StaffCreator();

    }

    @Transactional
    public List<Staff> getStaff() {
        StaffHelper staffHelper = new StaffHelper();
        List<Staff> staffList= staffHelper.listAllStaffs();
        Iterator<Staff> staffIterator = staffList.iterator();
        while (staffIterator.hasNext()){
            Staff staff=staffIterator.next();
            logger.error("staff.getname=============="+staff.getFullName());
           // Hibernate.initialize(staff.getStaffGeneralexamProfiles());
        }
        return staffList;
      //  return staffHelper.listAllStaffs();

    }

    @Transactional
    public Staff saveOrUpdate(Staff staff) {
        StaffHelper staffHelper = new StaffHelper();
        staffHelper.saveOrUpdate(staff);
        return staff;
    }

    @Transactional
    public List<Staff> getStaffsNameLike(String regx, int maxNo) {
        StaffHelper staffHelper = new StaffHelper();
        return staffHelper.getStaffsNameLike(regx, maxNo);
    }
    @Transactional
    public Staff deleteStaff(Staff staff){
        StaffHelper staffHelper=new StaffHelper();
        staff=staffHelper.staffDelete(staff);
        return staff;
    }
    }
