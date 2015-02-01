package com.rpgm.online.login;

import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yarlithub.yschool.init.dataAccess.InitDBQueries;
import org.yarlithub.yschool.repository.services.data.DataLayerYschool;
import org.yarlithub.yschool.repository.services.data.DataLayerYschoolImpl;


public class LoginHelper {
	 private static final Logger logger = LoggerFactory.getLogger(LoginHelper.class);
	 DataLayerYschool DataLayerYschool = DataLayerYschoolImpl.getInstance();
	 public boolean loginValidator(String userName,String password){
		SQLQuery selectUserIDQuery = DataLayerYschool.createSQLQuery(InitDBQueries.GET_USER_ID);
		selectUserIDQuery.setParameter("user_name", userName);
		selectUserIDQuery.setParameter("password", password);
		if(selectUserIDQuery.list().size()>0){
		int userID = Integer.valueOf(selectUserIDQuery.list().get(0).toString());
	    logger.debug("with in logIn metho..userID******.."+userID);
	    return true;
	 }else{
		 return false;
	 }
}
}