package org.yarlithub.yschool.student.core;

import org.yarlithub.yschool.repository.factories.yschool.YschoolDataPoolFactory;
import org.yarlithub.yschool.repository.model.obj.yschool.Student;
import org.yarlithub.yschool.repository.services.data.DataLayerYschool;
import org.yarlithub.yschool.repository.services.data.DataLayerYschoolImpl;

import java.util.Date;


public class StudentCreator {
    DataLayerYschool dataLayerYschool = DataLayerYschoolImpl.getInstance();

    public Student addNewStudent(String admission_No, String name, String fullname, String name_wt_initial, Date dob, String gender, String address,byte[] photo) {

        Student student = YschoolDataPoolFactory.getStudent();
        student.setAdmissionNo(admission_No);
        student.setName(name);
        student.setFullName(fullname);
        student.setNameWtInitial(name_wt_initial);
        student.setDob(dob);
        student.setGender(gender);
        student.setAddress(address);
        //TODO: hage to get bytestream to send database.
        student.setPhoto(photo);
        dataLayerYschool.save(student);
        dataLayerYschool.flushSession();
        return student;
    }

}
