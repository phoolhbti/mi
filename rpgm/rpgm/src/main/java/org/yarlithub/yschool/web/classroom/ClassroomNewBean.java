package org.yarlithub.yschool.web.classroom;

import org.primefaces.event.DragDropEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.yarlithub.yschool.repository.model.obj.yschool.Classroom;
import org.yarlithub.yschool.repository.model.obj.yschool.Grade;
import org.yarlithub.yschool.service.ClassroomService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: Jay Krish
 * Date: 1/5/13
 * Time: 8:20 AM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean
@Scope(value = "view")
@Controller
public class ClassroomNewBean implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
    private ClassroomService classroomService;


    private List<Classroom> classrooms;
    private List<Classroom> selectedClassrooms;
   // private List<Grade> grads;
    private List<SelectItem> options = new ArrayList<SelectItem>();

    @PostConstruct
    public void init(){
        classrooms = new ArrayList<Classroom>();
        selectedClassrooms = new ArrayList<Classroom>();
//
//        classrooms.add(new Classroom("Messi", 10, "messi.jpg", "forward"));
//        classrooms.add(new Classroom("Villa", 7, "villa.jpg", "forward"));
//        classrooms.add(new Classroom("Pedro", 17, "pedro.jpg", "forward"));
        this.options.add(new SelectItem(classroomService.getGrades()));

    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public List<Classroom> getSelectedClassrooms() {
        return selectedClassrooms;
    }

    /*public List<Grade> getGrads() {
    	return classroomService.getGrades();
	//	return grads;
	}

	public void setGrads(List<Grade> grads) {
		this.grads = grads;
	}*/

	public void onDrop(DragDropEvent event) {
        Classroom Classroom = (Classroom) event.getData();
        selectedClassrooms.add(Classroom);
    }

	public List<SelectItem> getOptions() {
		return options;
	}

	public void setOptions(List<SelectItem> options) {
		this.options = options;
	}


}
