package com.rpgm.online.subject.core;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.yarlithub.yschool.repository.model.obj.yschool.Subject;

import org.yarlithub.yschool.repository.services.data.DataLayerYschool;
import org.yarlithub.yschool.repository.services.data.DataLayerYschoolImpl;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Amaaniy
 * Date: 12/16/13
 * Time: 12:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class SubjectHelper {

    DataLayerYschool dataLayerYschool = DataLayerYschoolImpl.getInstance();

    /**
     *
     * @return
     */
    public List<Subject> listAllSubjects() {
        Criteria subjectCriteria = dataLayerYschool.createCriteria(Subject.class);
        return subjectCriteria.list();

    }
    public Subject saveOrUpdate(Subject subject) {
        dataLayerYschool.saveOrUpdate(subject);
        return subject;
    }
    public List<Subject> getSubjectsNameLike(String regx, int maxNo) {
        Criteria subjectCR = dataLayerYschool.createCriteria(Subject.class);
        subjectCR.add(Restrictions.like("name", regx, MatchMode.ANYWHERE));
        subjectCR.setMaxResults(maxNo);
        List<Subject> subjectList = subjectCR.list();
        return subjectList;
    }
    public Subject subjectDelete(Subject subject){
        dataLayerYschool.delete(subject);
        return subject;
    }

}
