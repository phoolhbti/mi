package org.yarlithub.yschool.web.staff;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.yarlithub.yschool.repository.model.obj.yschool.Staff;
import org.yarlithub.yschool.repository.model.obj.yschool.Student;
import org.yarlithub.yschool.service.StaffService;
import org.yarlithub.yschool.web.student.StudentListBean;

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
@Scope(value = "request")
@Controller
public class StaffHomeBean implements Serializable {
	private static final Logger logger = Logger.getLogger(StaffHomeBean.class);
    
	private DataModel<Staff> staffDataModel;
    //private UploadedFile photo;
    @Autowired
    private StaffService staffService;
    @Autowired
    private StaffController staffController;
    
    private String searchKey = null;
    
    private DataModel<Staff> staffsSearchResultAjax;
    private DataModel<Staff> staffsSearchResult;
    private String page = "staffSearch";
    private Staff staff;
    public StaffHomeBean() {
        super();
        staffsSearchResultAjax = new ListDataModel<Staff>();
    }
    @PostConstruct
    public void init() {
        staffDataModel = new ListDataModel(staffService.getStaff());
        this.setStaffDataModel(staffDataModel);
      //  return true;
    }

    public DataModel getStaffDataModel() {
        return staffDataModel;
    }

    public void setStaffDataModel(DataModel staffDataModel) {
        this.staffDataModel = staffDataModel;
    }

  /*  public String viewStaff() {
        Staff staff =(Staff)staffDataModel.getRowData();
        staffController.setStaff(staff);
        logger.error("This is Error message stall staffid======="+staff.getStaffid());
        return "ViewStaff";
    }
*/
	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public DataModel<Staff> getStaffsSearchResultAjax() {
		return staffsSearchResultAjax;
	}

	public void setStaffsSearchResultAjax(DataModel<Staff> staffsSearchResultAjax) {
		this.staffsSearchResultAjax = staffsSearchResultAjax;
	}

	public DataModel<Staff> getStaffsSearchResult() {
		return staffsSearchResult;
	}

	public void setStaffsSearchResult(DataModel<Staff> staffsSearchResult) {
		this.staffsSearchResult = staffsSearchResult;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	 public String viewStaffAjax(){
	        staffsSearchResultAjax = new ListDataModel<Staff>(staffService.getStaffsNameLike(searchKey,10));
	        setStaff(staffsSearchResultAjax.getRowData());
	        staffController.setStaff(staff);
	        return "viewStaffAjax";
	    }

	    public String viewStaffSearch(){
	        staffsSearchResult = new ListDataModel<Staff>(staffService.getStaffsNameLike(searchKey,30));
	        staffController.setStaffList(staffsSearchResult);
	        return "ViewStaffList";
	    }
	    public String viewStaff() {
	        Staff staff =(Staff)staffDataModel.getRowData();
	        staffController.setStaff(staff);
	        logger.error("This is Error message stall staffid======="+staff.getStaffid());
	        return "viewStaff";
	    }
}
