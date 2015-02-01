package org.yarlithub.yschool.classroom.dataAccess;

/**
 * Created with IntelliJ IDEA.
 * User: Jay Krish
 * Date: 8/20/13
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClassroomDBQueries {
	public static final String GET_STAFF_NAME_BY_IDCLASSROOM = "select name from staff where idstaff=(select staff_idstaff from staff_has_role where idstaff_has_role=(select staff_has_role_idstaff_has_role from classroom_has_staff_has_role where classroom_idclassroom= :idclassroom))";
		//	"SELECT idstudent FROM student WHERE admission_no = :add_no" ;

  }
