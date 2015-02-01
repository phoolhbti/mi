package org.yarlithub.yschool.init.dataAccess;

/**
 * Created with IntelliJ IDEA.
 * User: Jay Krish
 * Date: 8/20/13
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class InitDBQueries {
    /**
     * TODO: description
      */
    public static final String CLASS_INIT_SQL = "INSERT INTO classroom (year, grade_idgrade, division_iddivision, section_idsection, modified_time) VALUES (:year, :grade_idgrade, :division_iddivision, :section_idsection, :modified_time)";
    public static final String SUBJECT_INIT_SQL =  "INSERT INTO subject (name, isOptional) VALUES (:name, :isOptional)";
    public static final String STAFF_INIT_SQL = "INSERT INTO staff (staffID, name, full_name, photo) VALUES (:id, :name, :full_name, :photo)";
    public static final String STUDENT_INIT_SQL =  "INSERT INTO student (admission_no, name, full_name, name_wt_initial, dob, gender, address, photo) VALUES (:admission_no, :name, :full_name, :name_wt_initial, :dob, :gender, :address, :photo)";
    public static final String GET_CLASS_ID_SQL = "SELECT idclassroom FROM classroom WHERE grade_idgrade = :grade_idgrade AND division_iddivision = :division_iddivision";
    public static final String GET_STUDENT_ID_SQL = "SELECT idstudent FROM student WHERE admission_no = :admission_no";
    public static final String CLASS_STUDENT_INIT_SQL =  "INSERT INTO classroom_student (classroom_idclassroom, student_idstudent) VALUES (:idClass, :idStudent)";

    public static final String INSERT_USER= "INSERT INTO user (user_name, email, password, user_role_iduser_role,modified_time) VALUES (:user_name, :email, :password, :User_Role_idUser_Role, :modified_time)";
    public static final String INSERT_SCHOOL = "INSERT INTO school (name, address, zone, district, province) VALUES (:name, :address, :zone, :district, :province)" ;
    
    public static final String GET_USER_ID= "SELECT iduser FROM user WHERE user_name = :user_name AND password = :password";
   
    public static final String GET_GRADE_ID= "SELECT idgrade FROM grade WHERE grade = :grade";
    public static final String GET_DIVISION_ID= "SELECT iddivision FROM division WHERE division = :division";
    public static final String GET_SECTION_ID= "SELECT idsection FROM section WHERE section_name = :section_name";
    public static final String GET_STAFF_ID="SELECT idstaff FROM staff WHERE full_name = :full_name AND name = :name";
    public static final String GET_STAFF_ID_BY_FULLNAME="SELECT idstaff FROM staff WHERE full_name = :full_name";
    public static final String GET_ROLE_ID="SELECT idrole FROM role WHERE role_name =:role_name";
    public static final String GET_STAFF_ROLE_ID="SELECT idstaff_has_role FROM staff_has_role WHERE staff_idstaff =:staff_idstaff";
    public static final String STAFF_ROLE_INIT_SQL="INSERT INTO staff_has_role (role_idrole, staff_idstaff, start_date) VALUES (:role_idrole, :staff_idstaff, :start_date)";
    public static final String CLASSROOM_HAS_STAFF_HAS_ROLEL_INST = "INSERT INTO classroom_has_staff_has_role (classroom_idclassroom, staff_has_role_idstaff_has_role) VALUES (:classroom_idclassroom, :staff_has_role_idstaff_has_role)";
}
