package org.yarlithub.yschool.init.ySchoolSetUp.Loader;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yarlithub.yschool.spreadSheetHandler.Reader;
import org.yarlithub.yschool.repository.factories.yschool.YschoolDataPoolFactory;
import org.yarlithub.yschool.repository.model.obj.yschool.Role;
import org.yarlithub.yschool.repository.services.data.DataLayerYschool;
import org.yarlithub.yschool.repository.services.data.DataLayerYschoolImpl;


/**
 * Created with eclipse
 * User: Phool Chandra
 * Date: 11 January 2015
 * Time: 11:16 PM
 * 
 */

public class RoleLoader {
	private static final Logger logger = LoggerFactory.getLogger(RoleLoader.class);
    public boolean load(Reader reader) {
    	logger.debug("with in rol loader load metod........");

        /**
         * In initialization document 6th sheet is Role information.
         */
        reader.setSheet(3);

        DataLayerYschool DataLayerYschool = DataLayerYschoolImpl.getInstance();
        for (int i = 1; i <= reader.getLastRowNumber(); i++) {
        	logger.debug("with in for loop............");
        	
            reader.setRow(i);
           // String name = reader.getStringCellValue(1);
            String roleName = reader.getStringCellValue(0);         
            logger.debug("Role Name----------"+roleName);          
            Role role = YschoolDataPoolFactory.getRole();
            logger.debug("after rol in .....");
            role.setRoleName(roleName);
            DataLayerYschool.save(role);
            logger.debug("after the save method......");
           
        }
        DataLayerYschool.flushSession();
        return true;
    }
}
