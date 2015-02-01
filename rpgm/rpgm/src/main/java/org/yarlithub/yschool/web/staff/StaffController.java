package org.yarlithub.yschool.web.staff;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.yarlithub.yschool.repository.model.obj.yschool.Staff;
import org.yarlithub.yschool.repository.model.obj.yschool.Student;

import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;

import java.io.Serializable;


/**
 * $LastChangedDate$
 * $LastChangedBy$
 * $LastChangedRevision$
 */

@ManagedBean
@Scope(value = "session")
@Controller
public class StaffController implements Serializable {


    private Staff staff;
    private DataModel<Staff> staffList;

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

	public DataModel<Staff> getStaffList() {
		return staffList;
	}

	public void setStaffList(DataModel<Staff> staffList) {
		this.staffList = staffList;
	}
}
