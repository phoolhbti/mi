package org.yarlithub.yschool.web.setup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.yarlithub.yschool.service.InitService;
import org.yarlithub.yschool.web.commons.ErrorBean;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: jaykrish
 * Date: 4/25/13
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */


@ManagedBean
@Scope(value = "session")
@Controller
public class SetupBean implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = -3134641068385215522L;
	private static final Logger logger = LoggerFactory.getLogger(SetupBean.class);
    //Strings related to user information
    private String userName;
    private String usereMail;
    private int userRole;
    private String password;
    private String confirmPassword;
    //have to get in from userRole string later
    private int adminUser;
    //Strings related to school information
    private String schoolName;
    private String schoolAddress;
    private String schoolZone;
    private String schoolDistrict;
    private String schoolProvience;
    /**
     * The path of the ySchool initiation document in the user's machine.
     */
    private String initDocPath;
    private UploadedFile initFile;
    @Autowired
    private InitService initService;


    public SetupBean() {
        logger.info("initiating a new setup bean");

    }

    public UploadedFile getInitFile() {
        return initFile;
    }

    public void setInitFile(UploadedFile initFile) {
        this.initFile = initFile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUsereMail() {
        return usereMail;
    }

    public void setUsereMail(String usereMail) {
        this.usereMail = usereMail;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
        //TODO: various user levels.
       // adminUser = 1;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getSchoolZone() {
        return schoolZone;
    }

    public void setSchoolZone(String schoolZone) {
        this.schoolZone = schoolZone;
    }

    public String getSchoolDistrict() {
        return schoolDistrict;
    }

    public void setSchoolDistrict(String schoolDistrict) {
        this.schoolDistrict = schoolDistrict;
    }

    public String getSchoolProvience() {
        return schoolProvience;
    }

    public void setSchoolProvience(String schoolProvience) {
        this.schoolProvience = schoolProvience;
    }

    public String getInitDocPath() {
        return initDocPath;
    }

    public void setInitDocPath(String initDocPath) {
        this.initDocPath = initDocPath;
    }

    public String enterSetup() {
        boolean setupResult = false;
        try {
            logger.info("Entering into first time ySchool setup");
            logger.error("Entering into first time ySchool setup error");

            setupResult = initService.ySchoolSetUP(userName, usereMail, password, schoolName, schoolAddress, schoolZone, schoolDistrict,
                    schoolProvience, initFile);
            logger.error("*******************setupResult-------"+setupResult);
            if (setupResult) {
            	logger.error("Setup Success");
                logger.info("Setup Success");
                //navigates to home page.(see faces-config.xml)
                return "success";
            }
        } catch (Exception e) {
        	 logger.error("setup failure \n"+e);
            logger.info("setup failure \n"+e);
            ErrorBean errorBean = new ErrorBean();
            errorBean.setErrorMessage(e.toString());
            return "failure";
//        } finally {
//            //shows error page.
//            return "failure";
        }
          return null;
    }

}
