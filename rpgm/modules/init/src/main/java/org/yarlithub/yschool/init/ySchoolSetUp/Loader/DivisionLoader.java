package org.yarlithub.yschool.init.ySchoolSetUp.Loader;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.yarlithub.yschool.init.ySchoolSetUp.Loader.GradeLoader;
import org.yarlithub.yschool.repository.factories.yschool.YschoolDataPoolFactory;
import org.yarlithub.yschool.repository.model.obj.yschool.Division;
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
public class DivisionLoader {
	 private static final Logger logger = LoggerFactory.getLogger(DivisionLoader.class);
    public boolean load(Reader reader) throws Exception{

        /**
         * In initialization document 2th sheet is division information.
         */
        reader.setSheet(1);

        DataLayerYschool DataLayerYschool = DataLayerYschoolImpl.getInstance();

        for (int i = 1; i <= reader.getLastRowNumber(); i++) {
            reader.setRow(i);

            String division = reader.getStringCellValue(0);
            logger.debug("divisiondivisiondivision %%%%%%%%%"+division);

            Division divisionInstance = YschoolDataPoolFactory.getDivision();
            divisionInstance.setDivision(division);

            DataLayerYschool.save(divisionInstance);
        }
        DataLayerYschool.flushSession();
        return true;
    }
}
