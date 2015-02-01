package org.yarlithub.yschool.staff.core;

import java.io.File;

import org.yarlithub.yschool.repository.factories.yschool.YschoolDataPoolFactory;
import org.yarlithub.yschool.repository.model.obj.yschool.Staff;
import org.yarlithub.yschool.repository.services.data.DataLayerYschool;
import org.yarlithub.yschool.repository.services.data.DataLayerYschoolImpl;
//import org.apache.myfaces.custom.fileupload.UploadedFile;
/**
 * Created with IntelliJ IDEA.
 * User: Amaaniy
 * Date: 12/16/13
 * Time: 12:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class StaffCreator {

    DataLayerYschool dataLayerYschool = DataLayerYschoolImpl.getInstance();

    public Staff addNewStaff(String staffID, String name, String fullname,byte photo[]) {

        Staff staff = YschoolDataPoolFactory.getStaff();
        staff.setStaffid(staffID);
        staff.setName(name);
        staff.setFullName(fullname);

        //TODO: hage to get Bytestream to send database.
        staff.setPhoto(photo);


        dataLayerYschool.save(staff);
        dataLayerYschool.flushSession();
        //TODO: save method does not indicates/returns success/failure
        return staff;
    }

//    public Staff addNewStaff(Staff staff) {
//        dataLayerYschool.save(staff);
//        dataLayerYschool.flushSession();
//
//        return staff;
//    }
}

