package org.yarlithub.yschool.init.ySchoolSetUp.Loader;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.SQLQuery;
import org.yarlithub.yschool.init.dataAccess.InitDBQueries;
import org.yarlithub.yschool.repository.services.data.DataLayerYschool;
import org.yarlithub.yschool.repository.services.data.DataLayerYschoolImpl;
import org.yarlithub.yschool.spreadSheetHandler.Reader;

/**
 * Created with IntelliJ IDEA.
 * User: Jay Krish
 * Date: 8/13/13
 * Time: 11:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class StaffLoader {
	private static final Logger logger=LoggerFactory.getLogger(StaffLoader.class);

    public boolean load(Reader reader) {

        /**
         * In initialization document 2th sheet is staff information.
         */
        reader.setSheet(4);
        DataLayerYschool DataLayerYschool = DataLayerYschoolImpl.getInstance();

        for (int i = 1; i <= reader.getLastRowNumber(); i++) {
            reader.setRow(i);
            int id = reader.getNumericCellValue(0);
            String name = reader.getStringCellValue(1);
            String fullName = reader.getStringCellValue(2);
            String roleName = reader.getStringCellValue(3);
            logger.debug("roleName----------"+roleName);
            logger.debug("emp fullName----------"+fullName);
            SQLQuery insertQuery = DataLayerYschool.createSQLQuery(InitDBQueries.STAFF_INIT_SQL);
            insertQuery.setParameter("id", id);
            insertQuery.setParameter("name", name);
            insertQuery.setParameter("full_name", fullName);
            insertQuery.setParameter("photo", null);
            int result = insertQuery.executeUpdate();
            logger.debug("result &&&&&&&"+result);
            SQLQuery selectIsStaffQuery = DataLayerYschool.createSQLQuery(InitDBQueries.GET_STAFF_ID);
            selectIsStaffQuery.setParameter("name", name);
            selectIsStaffQuery.setParameter("full_name", fullName);
            int staffID = Integer.valueOf(selectIsStaffQuery.list().get(0).toString());
            logger.debug("staffID-----"+staffID);
            if(!"".equals(roleName)){
            	logger.debug("with in if block start "+roleName);
            SQLQuery selectIdRoleQuery = DataLayerYschool.createSQLQuery(InitDBQueries.GET_ROLE_ID);
            selectIdRoleQuery.setParameter("role_name", roleName);
            int roleID = Integer.valueOf(selectIdRoleQuery.list().get(0).toString());
            logger.debug("roleID-@@@@@@@@@@@@@@@@@---"+roleID);
            SQLQuery insertStaffRoleQuery = DataLayerYschool.createSQLQuery(InitDBQueries.STAFF_ROLE_INIT_SQL);
            insertStaffRoleQuery.setParameter("role_idrole", roleID);
            insertStaffRoleQuery.setParameter("staff_idstaff", staffID);
            insertStaffRoleQuery.setParameter("start_date", new java.util.Date());
            int result2=insertStaffRoleQuery.executeUpdate();
            logger.debug("result &&&&&&&"+result2);
            }
            
        }
        return true;
    }
}
