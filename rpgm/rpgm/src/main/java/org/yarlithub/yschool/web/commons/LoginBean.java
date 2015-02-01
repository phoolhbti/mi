package org.yarlithub.yschool.web.commons;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.yarlithub.yschool.service.InitService;
import org.yarlithub.yschool.service.LoginService;
import org.yarlithub.yschool.web.util.LoginUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: JayKrish
 * Date: 4/25/13
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@Scope(value = "request")
@Controller
public class LoginBean implements Serializable {

    private static final Logger logger = Logger.getLogger(LoginBean.class);
    //string to test initial development of setup.xhtml file
    public String userName;
    public String password;
    @Autowired
    private InitService initService;
    @Autowired
    private LoginService loginService;

    public LoginBean() {
        logger.info("initiating a new setup bean");

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        logger.info("Entering into first time ySchool setup");
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "setting up now.", null));


        boolean result =loginService.logIn(userName, password);
        if(result){
        	logger.debug("with in success block...........");
		        HttpSession session = LoginUtil.getSession();
		        session.setAttribute("username", userName);
		        return "login";
        }else{
        	FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Invalid Login!",
                    "Please Try Again!"));
 
            // invalidate session, and redirect to other pages
 
            //message = "Invalid Login. Please Try Again!";
        	return "logout";
        }
    }
    public String logout() {
    	logger.debug("with in logout block..........");
        HttpSession session = LoginUtil.getSession();
        session.invalidate();
        return "logout";
    	//FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
       // return "/index?faces-redirect=true";
     }

}
