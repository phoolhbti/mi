package org.yarlithub.yschool.service;

import net.sf.jasperreports.engine.JRException;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yarlithub.yschool.analytics.core.YAnalyzer;
import org.yarlithub.yschool.analytics.datasync.SyncExamination;
import org.yarlithub.yschool.analytics.reporting.JasperReport;
import org.yarlithub.yschool.repository.model.obj.yschool.*;
import org.yarlithub.yschool.student.core.GetStudent;
import org.yarlithub.yschool.student.core.StudentHelper;

import javax.faces.model.DataModel;
import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jay Krish
 * Date: 9/22/13
 * Time: 9:05 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * TODO description
 */

@Service(value = "analyticsService")
public class AnalyticsService {
    private static final Logger logger = LoggerFactory.getLogger(AnalyticsService.class);

    @Transactional
    public List<ClassroomModule> getOLSubjects(Student student) {

        YAnalyzer yAnalyzer = new YAnalyzer();
        List<ClassroomModule> ClassroomModuleList = yAnalyzer.getOLSubjects(student);

        Iterator<ClassroomModule> ClassroomModuleIterator = ClassroomModuleList.iterator();
        while (ClassroomModuleIterator.hasNext()) {
            ClassroomModule ClassroomModule = ClassroomModuleIterator.next();
            Hibernate.initialize(ClassroomModule.getModuleIdmodule());
            // Hibernate.initialize(ClassroomModule.getExams());
        }

        return ClassroomModuleList;
    }

    @Transactional
    public List<ClassroomModule> getOLSubjectsEleven(Student student) {

        YAnalyzer yAnalyzer = new YAnalyzer();
        List<ClassroomModule> ClassroomModuleList = yAnalyzer.getOLSubjectsGradeEleven(student);

        Iterator<ClassroomModule> ClassroomModuleIterator = ClassroomModuleList.iterator();
        while (ClassroomModuleIterator.hasNext()) {
            ClassroomModule ClassroomModule = ClassroomModuleIterator.next();
            Hibernate.initialize(ClassroomModule.getModuleIdmodule());
            // Hibernate.initialize(ClassroomModule.getExams());
        }

        return ClassroomModuleList;
    }

    @Transactional
    public List<ClassroomModule> getALSubjects(Student student) {

        YAnalyzer yAnalyzer = new YAnalyzer();
        List<ClassroomModule> ClassroomModuleList = yAnalyzer.getALSubjects(student);
        if (ClassroomModuleList == null) {
            /*some students are unknown at AL streams yet*/
            return null;
        }
        Iterator<ClassroomModule> ClassroomModuleIterator = ClassroomModuleList.iterator();
        while (ClassroomModuleIterator.hasNext()) {
            ClassroomModule ClassroomModule = ClassroomModuleIterator.next();
            Hibernate.initialize(ClassroomModule.getModuleIdmodule());
            // Hibernate.initialize(ClassroomModule.getExams());
        }

        return ClassroomModuleList;
    }

    @Transactional
    public String getALSubjectsResult(Student student, ClassroomModule ClassroomModule) {
        YAnalyzer yAnalyzer = new YAnalyzer();
        return yAnalyzer.getALSubjectsResult(student, ClassroomModule);

    }

    @Transactional
    public String getOLSubjectsResult(Student student, ClassroomModule ClassroomModule) {
        YAnalyzer yAnalyzer = new YAnalyzer();
        return yAnalyzer.getOLSubjectsResult(student, ClassroomModule);

    }

    @Transactional
    public Student getStudenById(int id) {
        GetStudent getStudent = new GetStudent();
        Student student = getStudent.getStudentByID(id);
        //Hibernate needs lazy initialization of internal objects
        Hibernate.initialize(student.getClassroomStudents());
        Hibernate.initialize(student.getStudentGeneralexamProfiles());
        Iterator<ClassroomStudent> classroomStudentIterator = student.getClassroomStudents().iterator();
        while (classroomStudentIterator.hasNext()) {
            ClassroomStudent classroomStudent = classroomStudentIterator.next();
            Hibernate.initialize(classroomStudent.getClassroomIdclassroom());
            Hibernate.initialize(classroomStudent.getStudentClassroomModules());
        }
        return student;
    }

    @Transactional
    public Student getStudent() {
        GetStudent student = new GetStudent();
        return student.getStudentByID(2);

    }

    @Transactional
    public List<Student> getStudentsNameLike(String regx, int maxNo) {
       StudentHelper studentHelper = new StudentHelper();
        return studentHelper.getStudentsNameLike(regx,maxNo);

    }

    @Transactional
    public void printReport(ServletOutputStream servletOutputStream) throws IOException, JRException {       // ServletOutputStream servletOutputStream
        JasperReport jasperReport = new JasperReport();
       jasperReport.printJasperReport(servletOutputStream);                        //  servletOutputStream
    }

    @Transactional
    public List<Student> getStudentByAdmissionNumber(List<Integer> admissionNo) {
        StudentHelper studentHelper = new StudentHelper();
        List<Student> studentList = new ArrayList<Student>();
        Iterator<Integer> adminNoIterator = admissionNo.iterator();
        while (adminNoIterator.hasNext()) {

            int admissionNumber = adminNoIterator.next();
            Student student = studentHelper.getStudentByAdmissionNo(admissionNumber);
            Hibernate.initialize((StudentGeneralexamProfile) student.getStudentGeneralexamProfiles().toArray()[0]);
            studentList.add(student);
//            Hibernate.initialize(Student.class);
//            Hibernate.initialize(StudentGeneralexamProfile.class);

        }

        return studentList;
    }

    @Transactional
    public List<StudentGeneralexamProfile> getStudentGeneralExamProfileByStudentList(DataModel<Student> matchingStudentProfiles) {


        StudentHelper studentHelper = new StudentHelper();
        List<StudentGeneralexamProfile> matchingProfilesGeneralExam = new ArrayList<StudentGeneralexamProfile>();
        Iterator<Student> matchingProfilesIterator = matchingStudentProfiles.iterator();
        while (matchingProfilesIterator.hasNext()) {

            Student student = matchingProfilesIterator.next();
            StudentGeneralexamProfile studentGeneralexamProfile = studentHelper.getStudentProfileViaStudentID(student);

            matchingProfilesGeneralExam.add(studentGeneralexamProfile);
        }
        return matchingProfilesGeneralExam;


    }

    @Transactional
    public List<Exam> getNotSyncedExams() {
        SyncExamination syncExamination = new SyncExamination();
        List<Exam> examList = syncExamination.getNotSyncedExams();
        //Hibernate needs lazy initialization of internal objects
        Iterator<Exam> iterator = examList.iterator();
        while (iterator.hasNext()) {
            Exam exam = iterator.next();
            Hibernate.initialize(exam.getClassroomModuleIdclassroomModule().getClassroomIdclassroom());
            Hibernate.initialize(exam.getClassroomModuleIdclassroomModule().getModuleIdmodule());
            Hibernate.initialize(exam.getExamTypeIdexamType());
            Hibernate.initialize(exam.getResultss());
            Hibernate.initialize(exam.getMarkss());
            Iterator<Results> resultsIterator = exam.getResultss().iterator();
            while(resultsIterator.hasNext()){
                Results results = resultsIterator.next();
                Hibernate.initialize(results);
                Hibernate.initialize(results.getStudentIdstudent());
            }
            Iterator<Marks> marksIterator = exam.getMarkss().iterator();
            while(marksIterator.hasNext()){
                Marks marks = marksIterator.next();
                Hibernate.initialize(marks);
                Hibernate.initialize(marks.getStudentIdstudent());
            }
        }
        return examList;

    }

    @Transactional
    public String PushNewExam(Exam exam){
     SyncExamination syncExamination = new SyncExamination();
        return syncExamination.PushNewExam(exam);
    }

    @Transactional
    public int getStudentIslandRank(Student student) {

        YAnalyzer yAnalyzer = new YAnalyzer();
        return yAnalyzer.getStudentIslandRank(student);

    }

    @Transactional
    public double getStudentzScore(Student student) {
        YAnalyzer yAnalyzer = new YAnalyzer();
        return yAnalyzer.getStudentZscore(student);

    }

    @Transactional
    public String checkStream(Student student) {
        YAnalyzer yAnalyzer = new YAnalyzer();
        return yAnalyzer.checkStream(student);

    }

    @Transactional
    public double getTermMarksForOLSub(Student student, ClassroomModule ClassroomModule, int term) {
        YAnalyzer yAnalyzer = new YAnalyzer();
        return yAnalyzer.getTermMarksForOLSub(student, ClassroomModule, term);
    }


}
