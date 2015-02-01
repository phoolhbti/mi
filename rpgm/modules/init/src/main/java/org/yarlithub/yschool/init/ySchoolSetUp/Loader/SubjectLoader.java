package org.yarlithub.yschool.init.ySchoolSetUp.Loader;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yarlithub.yschool.spreadSheetHandler.Reader;
import org.yarlithub.yschool.repository.factories.yschool.YschoolDataPoolFactory;
import org.yarlithub.yschool.repository.model.obj.yschool.Subject;
import org.yarlithub.yschool.repository.services.data.DataLayerYschool;
import org.yarlithub.yschool.repository.services.data.DataLayerYschoolImpl;


/**
 * Created with IntelliJ IDEA.
 * User: Jay Krish
 * Date: 8/13/13
 * Time: 11:16 PM
 * To change this template use File | Settings | File Templates.
 */
//TODO: Database change to subjects and yearwise modules, have to change all according to.
public class SubjectLoader {
	private static final Logger logger = LoggerFactory.getLogger(SubjectLoader.class);
    public boolean load(Reader reader) {

        /**
         * In initialization document 3th scheet is subjects information.
         */
        reader.setSheet(2);


        DataLayerYschool DataLayerYschool = DataLayerYschoolImpl.getInstance();

        for (int i = 1; i <= reader.getLastRowNumber(); i++) {
            reader.setRow(i);

            //we use hibernate ORM for subject initiation (it works here due to no dependencies with other tables)

            
            String subjectCode = reader.getStringCellValue(0);
            String subjectName = reader.getStringCellValue(1);
            logger.debug("subjectName----------"+subjectName);
          /*  boolean isOptionalBool = false;
            if (isOptional == 1) {
                isOptionalBool = true;
            }*/

            Subject subject = YschoolDataPoolFactory.getSubject();
            subject.setSubjectName(subjectName);
            subject.setSubjectCode(subjectCode);

            DataLayerYschool.save(subject);
            DataLayerYschool.flushSession();


        }
        return true;
    }
}
