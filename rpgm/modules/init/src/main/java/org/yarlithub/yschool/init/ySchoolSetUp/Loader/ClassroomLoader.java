package org.yarlithub.yschool.init.ySchoolSetUp.Loader;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yarlithub.yschool.init.dataAccess.InitDBQueries;
import org.yarlithub.yschool.repository.factories.yschool.YschoolDataPoolFactory;
import org.yarlithub.yschool.repository.model.obj.yschool.Classroom;
import org.yarlithub.yschool.repository.model.obj.yschool.Division;
import org.yarlithub.yschool.repository.model.obj.yschool.Grade;
import org.yarlithub.yschool.repository.model.obj.yschool.Section;
import org.yarlithub.yschool.repository.model.obj.yschool.Subject;
import org.yarlithub.yschool.repository.services.data.DataLayerYschool;
import org.yarlithub.yschool.repository.services.data.DataLayerYschoolImpl;
import org.yarlithub.yschool.spreadSheetHandler.Reader;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: Jay Krish
 * Date: 8/13/13
 * Time: 11:16 PM
 * To change this template use File | Settings | File Templates.
 * Updated by Phool Chandra
 */
public class ClassroomLoader {
	private static final Logger logger = LoggerFactory.getLogger(ClassroomLoader.class);
    public boolean load(Reader reader) throws Exception {

        /**
         * In initialization document 3th sheet is classroom information.
         */
        reader.setSheet(5);
        DataLayerYschool DataLayerYschool = DataLayerYschoolImpl.getInstance();

        for (int i = 1; i <= reader.getLastRowNumber(); i++) {
            reader.setRow(i);

            int grade = reader.getNumericCellValue(0);
            String division = reader.getStringCellValue(1);
            int year = reader.getNumericCellValue(2);
            String classTecherName= reader.getStringCellValue(3);
            //TODO:get it from system?properties?
            //int year = 2014;
            logger.debug("Start of ClassroomLoader..........");
            SQLQuery insertQuery = DataLayerYschool.createSQLQuery(InitDBQueries.CLASS_INIT_SQL);
            insertQuery.setParameter("year", year);
            SQLQuery selectGradeIDQuery = DataLayerYschool.createSQLQuery(InitDBQueries.GET_GRADE_ID);
            selectGradeIDQuery.setParameter("grade", grade);
            int gradeID = Integer.valueOf(selectGradeIDQuery.list().get(0).toString());
            logger.debug("gradeID---"+gradeID);
            SQLQuery selectDivisionIDQuery = DataLayerYschool.createSQLQuery(InitDBQueries.GET_DIVISION_ID);
            selectDivisionIDQuery.setParameter("division", division);
            int divisionID = Integer.valueOf(selectDivisionIDQuery.list().get(0).toString());
            logger.debug("divisionID---"+divisionID);
            insertQuery.setParameter("grade_idgrade", gradeID);
            insertQuery.setParameter("division_iddivision", divisionID);
            insertQuery.setParameter("section_idsection", null);
            insertQuery.setParameter("modified_time",new java.util.Date());
            int result = insertQuery.executeUpdate();
            logger.debug("after result  of ClassroomLoader.........."+result);

          if((classTecherName!=null)&&(!"".equals(classTecherName))){
			        logger.debug("with in if block...............");
			        SQLQuery selectClassIDQuery = DataLayerYschool.createSQLQuery(InitDBQueries.GET_CLASS_ID_SQL);
			        selectClassIDQuery.setParameter("grade_idgrade", gradeID);
			        selectClassIDQuery.setParameter("division_iddivision", divisionID);            
			        int classID = Integer.valueOf(selectClassIDQuery.list().get(0).toString());
			        logger.debug("result of  classID...."+classID);
			        
			        SQLQuery selectIsStaffQuery = DataLayerYschool.createSQLQuery(InitDBQueries.GET_STAFF_ID_BY_FULLNAME);
			        //selectIsStaffQuery.setParameter("name", name);
			        selectIsStaffQuery.setParameter("full_name", classTecherName);
			        int staffID = Integer.valueOf(selectIsStaffQuery.list().get(0).toString());
			        logger.debug("staffID-----"+staffID);
			       
			        SQLQuery selectIdclassStaffQuery = DataLayerYschool.createSQLQuery(InitDBQueries.GET_STAFF_ROLE_ID);
			        //selectIsStaffQuery.setParameter("name", name);
			        selectIdclassStaffQuery.setParameter("staff_idstaff", staffID);
			        int staffidrole = Integer.valueOf(selectIdclassStaffQuery.list().get(0).toString());
			        logger.debug("staffidrole-----"+staffidrole);
			        SQLQuery insertQueryclassroomhasstaffrole = DataLayerYschool.createSQLQuery(InitDBQueries.CLASSROOM_HAS_STAFF_HAS_ROLEL_INST);
			        insertQueryclassroomhasstaffrole.setParameter("staff_has_role_idstaff_has_role", staffidrole);
			        insertQueryclassroomhasstaffrole.setParameter("classroom_idclassroom", classID);
			        
			       int result4= insertQueryclassroomhasstaffrole.executeUpdate();
			       logger.debug("result4----------&&&&"+result4);
        }
        
        
        }
        return true;
    }
}
