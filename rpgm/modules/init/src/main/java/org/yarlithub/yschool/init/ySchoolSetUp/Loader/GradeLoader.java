package org.yarlithub.yschool.init.ySchoolSetUp.Loader;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yarlithub.yschool.repository.factories.yschool.YschoolDataPoolFactory;
import org.yarlithub.yschool.repository.model.obj.yschool.Grade;
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
public class GradeLoader {
	 private static final Logger logger = LoggerFactory.getLogger(GradeLoader.class);

    public boolean load(Reader reader) throws Exception{

        /**
         * In initialization document 1th sheet is grades information.
         */
    	logger.debug("start of load method.....");
        reader.setSheet(0);

        DataLayerYschool DataLayerYschool = DataLayerYschoolImpl.getInstance();

        for (int i = 1; i <= reader.getLastRowNumber(); i++) {
            reader.setRow(i);
            logger.debug("with in for loop..........");
            int grade = reader.getNumericCellValue(0);
            logger.debug("grad value....is..."+grade);
            Grade gradeInstance = YschoolDataPoolFactory.getGrade();
            gradeInstance.setGrade(grade);

            DataLayerYschool.save(gradeInstance);
        }
        DataLayerYschool.flushSession();
        return true;
    }
}
