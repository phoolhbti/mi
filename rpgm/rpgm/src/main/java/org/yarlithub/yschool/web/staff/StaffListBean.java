package org.yarlithub.yschool.web.staff;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.yarlithub.yschool.repository.model.obj.yschool.Classroom;
import org.yarlithub.yschool.repository.model.obj.yschool.Staff;
import org.yarlithub.yschool.service.StaffService;


@ManagedBean
@Scope(value = "view")
@Controller
public class StaffListBean implements Serializable {
	private static final Logger logger = Logger.getLogger(StaffListBean.class);
	private DataModel<Staff> listStaffs;
    @Autowired
    private StaffService staffService;
    @Autowired
    private StaffController staffController;
    
    @PostConstruct
    public void init() {
       // Classroom classroom = staffController.getClassroom();
        listStaffs = staffController.getStaffList();
    }

	public DataModel<Staff> getListStaffs() {
		return listStaffs;
	}

	public void setListStaffs(DataModel<Staff> listStaffs) {
		this.listStaffs = listStaffs;
	}

	 public String viewStaff(){
		 Staff staff=(Staff)listStaffs.getRowData();
		 staffController.setStaff(staff);
		 return "viewStaff";
	 }
}
