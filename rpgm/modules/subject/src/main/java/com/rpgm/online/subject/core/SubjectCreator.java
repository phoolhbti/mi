package com.rpgm.online.subject.core;



import org.yarlithub.yschool.repository.factories.yschool.YschoolDataPoolFactory;
import org.yarlithub.yschool.repository.model.obj.yschool.Subject;
import org.yarlithub.yschool.repository.services.data.DataLayerYschool;
import org.yarlithub.yschool.repository.services.data.DataLayerYschoolImpl;
//import org.apache.myfaces.custom.fileupload.UploadedFile;
/**
 * Created with IntelliJ IDEA.
 * User: Amaaniy
 * Date: 12/16/13
 * Time: 12:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class SubjectCreator {

    DataLayerYschool dataLayerYschool = DataLayerYschoolImpl.getInstance();

    public Subject addNewSubject(String subject_id, String subject_name) {

        Subject subject = YschoolDataPoolFactory.getSubject();
        subject.setSubjectCode(subject_id);
        subject.setSubjectName(subject_name);
        
        dataLayerYschool.save(subject);
        dataLayerYschool.flushSession();
        //TODO: save method does not indicates/returns success/failure
        return subject;
    }

//    public Staff addNewStaff(Staff staff) {
//        dataLayerYschool.save(staff);
//        dataLayerYschool.flushSession();
//
//        return staff;
//    }
}

