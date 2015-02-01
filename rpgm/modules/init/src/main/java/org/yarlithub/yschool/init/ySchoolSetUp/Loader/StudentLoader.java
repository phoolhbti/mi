package org.yarlithub.yschool.init.ySchoolSetUp.Loader;

import org.hibernate.SQLQuery;
import org.yarlithub.yschool.spreadSheetHandler.Reader;
import org.yarlithub.yschool.repository.services.data.DataLayerYschool;
import org.yarlithub.yschool.repository.services.data.DataLayerYschoolImpl;
import org.yarlithub.yschool.init.dataAccess.InitDBQueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Jay Krish
 * Date: 8/13/13
 * Time: 11:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class StudentLoader {
	 private static final Logger logger = LoggerFactory.getLogger(StudentLoader.class);
    public boolean load(Reader reader) {

        /**
         * In initialization document 1th sheet is student information.
         */
        reader.setSheet(6);
        DataLayerYschool DataLayerYschool = DataLayerYschoolImpl.getInstance();

        for (int i = 1; i <= reader.getLastRowNumber(); i++) {
            reader.setRow(i);

            int addmissionNo = reader.getNumericCellValue(0);
            String nameWithInitials = reader.getStringCellValue(1);
            String fullName = reader.getStringCellValue(2);
            String dob = reader.getStringCellValue(3);
            String gender = reader.getStringCellValue(4);
            String address = reader.getStringCellValue(5);
            int grade = reader.getNumericCellValue(6);
            String division = reader.getStringCellValue(7);

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date parsed = null;
            try {
                parsed = format.parse(dob);
            } catch (ParseException e) {
            	logger.error("error ----"+e);
            }
            java.sql.Date sqldob = new java.sql.Date(parsed.getTime());

            //insert Student table
            SQLQuery insertStudentQuery = DataLayerYschool.createSQLQuery(InitDBQueries.STUDENT_INIT_SQL);
            insertStudentQuery.setParameter("admission_no", addmissionNo);
            insertStudentQuery.setParameter("name", nameWithInitials);      //TODO: name
            insertStudentQuery.setParameter("full_name", fullName);
            insertStudentQuery.setParameter("name_wt_initial", nameWithInitials);
            insertStudentQuery.setParameter("dob", sqldob);
            insertStudentQuery.setParameter("gender", gender);
            insertStudentQuery.setParameter("address", address);
            insertStudentQuery.setParameter("photo", null);
            int result = insertStudentQuery.executeUpdate();
            logger.debug("result of student insertionn...."+result);
            //get classID, StudentID and insert into class_student relation table
            SQLQuery selectClassIDQuery = DataLayerYschool.createSQLQuery(InitDBQueries.GET_CLASS_ID_SQL);
            
            SQLQuery selectGradeIDQuery = DataLayerYschool.createSQLQuery(InitDBQueries.GET_GRADE_ID);
            selectGradeIDQuery.setParameter("grade", grade);
            int gradeID = Integer.valueOf(selectGradeIDQuery.list().get(0).toString());
            
            SQLQuery selectDivisionIDQuery = DataLayerYschool.createSQLQuery(InitDBQueries.GET_DIVISION_ID);
            selectDivisionIDQuery.setParameter("division", division);
            int divisionID = Integer.valueOf(selectDivisionIDQuery.list().get(0).toString());
            
           
            selectClassIDQuery.setParameter("grade_idgrade", gradeID);
            selectClassIDQuery.setParameter("division_iddivision", divisionID);
            
            int classID = Integer.valueOf(selectClassIDQuery.list().get(0).toString());
            logger.debug("result of student classID...."+classID);
            SQLQuery selectStudentIDQuery = DataLayerYschool.createSQLQuery(InitDBQueries.GET_STUDENT_ID_SQL);
            selectStudentIDQuery.setParameter("admission_no", addmissionNo);
            int studentID = Integer.valueOf(selectStudentIDQuery.list().get(0).toString());
            logger.debug("studentID****************...."+studentID);
            SQLQuery insertClassStudentQuery = DataLayerYschool.createSQLQuery(InitDBQueries.CLASS_STUDENT_INIT_SQL);
            insertClassStudentQuery.setParameter("idClass", classID);
            insertClassStudentQuery.setParameter("idStudent", studentID);
            int result2 = insertClassStudentQuery.executeUpdate();
            logger.debug("result2****************...."+result2);
        }
        return true;
    }
}
