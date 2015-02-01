package org.yarlithub.yschool.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rpgm.online.login.LoginHelper;


@Service(value = "loginService")
public class LoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	//static DataLayerYschool DataLayerYschool = DataLayerYschoolImpl.getInstance();
	 @Transactional
	    public boolean logIn(String userName, String password) {
		 LoginHelper loginhelper=new LoginHelper();
		 boolean resultBoolan=loginhelper.loginValidator(userName, password);
		 //SQLQuery selectUserIDQuery = DataLayerYschool.createSQLQuery(InitDBQueries.GET_USER_ID);
		 //selectUserIDQuery.setParameter("user_name", userName);
		 //selectUserIDQuery.setParameter("password", password);
		 //if(selectUserIDQuery.list().size()>0){
		// int userID = Integer.valueOf(selectUserIDQuery.list().get(0).toString());
	      logger.debug("with in logIn metho..userID******.."+resultBoolan);
	      return resultBoolan;
		 /*}
		 else
		 {
			 return false;
			 }
	    	*/
	    }

}
