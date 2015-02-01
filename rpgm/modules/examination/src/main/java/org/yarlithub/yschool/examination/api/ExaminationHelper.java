package org.yarlithub.yschool.examination.api;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.yarlithub.yschool.repository.model.obj.yschool.Exam;
import org.yarlithub.yschool.repository.model.obj.yschool.Marks;
import org.yarlithub.yschool.repository.model.obj.yschool.Results;
import org.yarlithub.yschool.repository.services.data.DataLayerYschool;
import org.yarlithub.yschool.repository.services.data.DataLayerYschoolImpl;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jay Krish
 * Date: 10/25/13
 * Time: 12:20 AM
 * To change this template use File | Settings | File Templates.
 */


public class ExaminationHelper {

    static DataLayerYschool dataLayerYschool = DataLayerYschoolImpl.getInstance();

    /**
     * Returns the Exam specified by id.
     *
     * @param examId
     * @return org.yarlithub.yschool.repository.model.obj.yschool.Exam
     */
    public static Exam getExamById(int examId) {
        Exam exam = dataLayerYschool.getExam(examId);
        return exam;
    }

    /**
     * Retrieve last inserted exam entries as Exam objects ordered by id:autoincrement .
     *
     * @param first The first entry, for pagination.
     * @param max   Maximum objects to retrieve.
     * @return List of latest Exam objects, org.yarlithub.yschool.repository.model.obj.yschool.Marks.
     */
    public static List<Exam> getLatestExams(int first, int max) {
        Criteria examCriteria = dataLayerYschool.createCriteria(Exam.class);
        examCriteria.addOrder(Order.desc("id"));
        examCriteria.setFirstResult(first);
        examCriteria.setMaxResults(max);
        List<Exam> examlist = examCriteria.list();
        return examlist;
    }

    /**
     * Returns Marks list of specified exam id.
     *
     * @param examId Requested exam id.
     * @return List of org.yarlithub.yschool.repository.model.obj.yschool.Marks
     */
    public static List<Marks> getExamMarks(int examId) {
        Exam exam = dataLayerYschool.getExam(examId);
        Criteria marksCriteria = dataLayerYschool.createCriteria(Marks.class);
        marksCriteria.add(Restrictions.eq("examIdexam", exam));
        return marksCriteria.list();
    }

    /**
     * Returns Results list of specified exam id.
     *
     * @param examId Requested exam id.
     * @return org.yarlithub.yschool.repository.model.obj.yschool.Results
     */
    public static List<Results> getExamResults(int examId) {
        Exam exam = dataLayerYschool.getExam(examId);
        Criteria resultsCriteria = dataLayerYschool.createCriteria(Results.class);
        resultsCriteria.add(Restrictions.eq("examIdexam", exam));
        return resultsCriteria.list();
    }
}
