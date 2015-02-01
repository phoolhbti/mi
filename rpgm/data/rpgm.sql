SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS rpgm DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE rpgm ;

-- -----------------------------------------------------
-- Table rpgm.school
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.school ;

CREATE  TABLE IF NOT EXISTS rpgm.school (
  idschool INT NOT NULL AUTO_INCREMENT ,
  name VARCHAR(45) NOT NULL ,
  school_no INT NULL ,
  address VARCHAR(45) NULL ,
  zone VARCHAR(45) NULL ,
  district VARCHAR(45) NULL ,
  province VARCHAR(45) NULL ,
  app_key VARCHAR(45) NULL ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (idschool) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.section
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.section ;

CREATE  TABLE IF NOT EXISTS rpgm.section (
  idsection INT NOT NULL AUTO_INCREMENT ,
  section_name VARCHAR(45) NULL ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (idsection) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.grade
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.grade ;

CREATE  TABLE IF NOT EXISTS rpgm.grade (
  idgrade INT NOT NULL AUTO_INCREMENT ,
  grade INT NOT NULL ,
  PRIMARY KEY (idgrade) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.division
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.division ;

CREATE  TABLE IF NOT EXISTS rpgm.division (
  iddivision INT NOT NULL AUTO_INCREMENT ,
  division VARCHAR(45) NOT NULL ,
  PRIMARY KEY (iddivision) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.classroom
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.classroom ;

CREATE  TABLE IF NOT EXISTS rpgm.classroom (
  idclassroom INT NOT NULL AUTO_INCREMENT ,
  year INT NOT NULL ,
  grade_idgrade INT NOT NULL ,
  division_iddivision INT NOT NULL ,
  section_idsection INT NULL ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (idclassroom) ,
  INDEX fk_classroom_section1_idx (section_idsection ASC) ,
  INDEX fk_classroom_grade1_idx (grade_idgrade ASC) ,
  INDEX fk_classroom_division1_idx (division_iddivision ASC) ,
  CONSTRAINT fk_classroom_section1
    FOREIGN KEY (section_idsection )
    REFERENCES rpgm.section (idsection )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_classroom_grade1
    FOREIGN KEY (grade_idgrade )
    REFERENCES rpgm.grade (idgrade )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_classroom_division1
    FOREIGN KEY (division_iddivision )
    REFERENCES rpgm.division (iddivision )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.staff
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.staff ;

CREATE  TABLE IF NOT EXISTS rpgm.staff (
  idstaff INT NOT NULL AUTO_INCREMENT ,
  staffID VARCHAR(45) NOT NULL ,
  name VARCHAR(45) NOT NULL ,
  full_name VARCHAR(100) NULL ,
  photo BLOB NULL ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (idstaff) ,
  UNIQUE INDEX staffID_UNIQUE (staffID ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.student
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.student ;

CREATE  TABLE IF NOT EXISTS rpgm.student (
  idstudent INT NOT NULL AUTO_INCREMENT ,
  admission_no VARCHAR(45) NOT NULL ,
  name VARCHAR(45) NOT NULL ,
  full_name VARCHAR(45) NULL ,
  name_wt_initial VARCHAR(45) NULL ,
  dob DATE NULL ,
  gender VARCHAR(10) NULL ,
  address VARCHAR(400) NULL ,
  photo LONGBLOB NULL ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (idstudent) ,
  UNIQUE INDEX addmision_no_UNIQUE (admission_no ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.exam_type
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.exam_type ;

CREATE  TABLE IF NOT EXISTS rpgm.exam_type (
  idexam_type INT NOT NULL AUTO_INCREMENT ,
  type_name VARCHAR(45) NOT NULL ,
  PRIMARY KEY (idexam_type) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.subject
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.subject ;

CREATE  TABLE IF NOT EXISTS rpgm.subject (
  idsubject INT NOT NULL AUTO_INCREMENT ,
  subject_code VARCHAR(100) NULL ,
  subject_name VARCHAR(100) NOT NULL ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (idsubject) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.module
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.module ;

CREATE  TABLE IF NOT EXISTS rpgm.module (
  idmodule INT NOT NULL AUTO_INCREMENT ,
  subject_idsubject INT NOT NULL ,
  grade_idgrade INT NOT NULL ,
  is_optional TINYINT(1) NULL ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (idmodule) ,
  INDEX fk_module_subject1_idx (subject_idsubject ASC) ,
  INDEX fk_module_grade1_idx (grade_idgrade ASC) ,
  CONSTRAINT fk_module_subject1
    FOREIGN KEY (subject_idsubject )
    REFERENCES rpgm.subject (idsubject )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_module_grade1
    FOREIGN KEY (grade_idgrade )
    REFERENCES rpgm.grade (idgrade )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.classroom_module
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.classroom_module ;

CREATE  TABLE IF NOT EXISTS rpgm.classroom_module (
  idclassroom_module INT NOT NULL AUTO_INCREMENT ,
  classroom_idclassroom INT NOT NULL ,
  module_idmodule INT NOT NULL ,
  PRIMARY KEY (idclassroom_module) ,
  INDEX fk_classroom_module_module1_idx (module_idmodule ASC) ,
  INDEX fk_classroom_module_classroom1_idx (classroom_idclassroom ASC) ,
  CONSTRAINT fk_classroom_module_module1
    FOREIGN KEY (module_idmodule )
    REFERENCES rpgm.module (idmodule )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_classroom_module_classroom1
    FOREIGN KEY (classroom_idclassroom )
    REFERENCES rpgm.classroom (idclassroom )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.exam
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.exam ;

CREATE  TABLE IF NOT EXISTS rpgm.exam (
  idexam INT NOT NULL AUTO_INCREMENT ,
  date DATE NOT NULL ,
  term INT NOT NULL ,
  exam_type_idexam_type INT NULL ,
  classroom_module_idclassroom_module INT NULL ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (idexam) ,
  INDEX fk_exam_exam_type1_idx (exam_type_idexam_type ASC) ,
  INDEX fk_exam_classroom_module1_idx (classroom_module_idclassroom_module ASC) ,
  CONSTRAINT fk_exam_exam_type1
    FOREIGN KEY (exam_type_idexam_type )
    REFERENCES rpgm.exam_type (idexam_type )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_exam_classroom_module1
    FOREIGN KEY (classroom_module_idclassroom_module )
    REFERENCES rpgm.classroom_module (idclassroom_module )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.marks
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.marks ;

CREATE  TABLE IF NOT EXISTS rpgm.marks (
  idmarks INT NOT NULL AUTO_INCREMENT ,
  exam_idexam INT NOT NULL ,
  student_idstudent INT NOT NULL ,
  marks FLOAT NULL ,
  isabsent INT NOT NULL DEFAULT 0 ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (idmarks) ,
  INDEX fk_marks_exam1_idx (exam_idexam ASC) ,
  INDEX fk_marks_student1_idx (student_idstudent ASC) ,
  CONSTRAINT fk_marks_exam1
    FOREIGN KEY (exam_idexam )
    REFERENCES rpgm.exam (idexam )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_marks_student1
    FOREIGN KEY (student_idstudent )
    REFERENCES rpgm.student (idstudent )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.user_role
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.user_role ;

CREATE  TABLE IF NOT EXISTS rpgm.user_role (
  iduser_role INT NOT NULL AUTO_INCREMENT ,
  name VARCHAR(45) NOT NULL ,
  PRIMARY KEY (iduser_role) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.user
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.user ;

CREATE  TABLE IF NOT EXISTS rpgm.user (
  iduser INT NOT NULL AUTO_INCREMENT ,
  user_name VARCHAR(45) NOT NULL ,
  email VARCHAR(45) NULL ,
  password VARCHAR(45) NOT NULL ,
  user_role_iduser_role INT NOT NULL ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (iduser) ,
  INDEX fk_user_user_role_idx (user_role_iduser_role ASC) ,
  CONSTRAINT fk_user_user_role
    FOREIGN KEY (user_role_iduser_role )
    REFERENCES rpgm.user_role (iduser_role )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.role
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.role ;

CREATE  TABLE IF NOT EXISTS rpgm.role (
  idrole INT NOT NULL AUTO_INCREMENT ,
  role_name VARCHAR(45) NULL ,
  PRIMARY KEY (idrole) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.results
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.results ;

CREATE  TABLE IF NOT EXISTS rpgm.results (
  idresults INT NOT NULL AUTO_INCREMENT ,
  exam_idexam INT NOT NULL ,
  student_idstudent INT NOT NULL ,
  results VARCHAR(5) NULL ,
  isabsent INT NOT NULL DEFAULT 0 ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (idresults) ,
  INDEX fk_results_exam1_idx (exam_idexam ASC) ,
  INDEX fk_results_student1_idx (student_idstudent ASC) ,
  CONSTRAINT fk_results_exam1
    FOREIGN KEY (exam_idexam )
    REFERENCES rpgm.exam (idexam )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_results_student1
    FOREIGN KEY (student_idstudent )
    REFERENCES rpgm.student (idstudent )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.classroom_student
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.classroom_student ;

CREATE  TABLE IF NOT EXISTS rpgm.classroom_student (
  idclassroom_student INT NOT NULL AUTO_INCREMENT ,
  student_idstudent INT NOT NULL ,
  classroom_idclassroom INT NOT NULL ,
  PRIMARY KEY (idclassroom_student) ,
  INDEX fk_classroom_has_student_student1_idx (student_idstudent ASC) ,
  INDEX fk_classroom_student_classroom1_idx (classroom_idclassroom ASC) ,
  CONSTRAINT fk_classroom_has_student_student1
    FOREIGN KEY (student_idstudent )
    REFERENCES rpgm.student (idstudent )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_classroom_student_classroom1
    FOREIGN KEY (classroom_idclassroom )
    REFERENCES rpgm.classroom (idclassroom )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.student_classroom_module
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.student_classroom_module ;

CREATE  TABLE IF NOT EXISTS rpgm.student_classroom_module (
  idstudent_classroom_module INT NOT NULL AUTO_INCREMENT ,
  classroom_student_idclassroom_student INT NOT NULL ,
  classroom_module_idclassroom_module INT NOT NULL ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (idstudent_classroom_module) ,
  INDEX fk_classroom_student_has_classroom_subject_classroom_studen_idx (classroom_student_idclassroom_student ASC) ,
  INDEX fk_student_classroom_subject_classroom_module1_idx (classroom_module_idclassroom_module ASC) ,
  CONSTRAINT fk_classroom_student_has_classroom_subject_classroom_student1
    FOREIGN KEY (classroom_student_idclassroom_student )
    REFERENCES rpgm.classroom_student (idclassroom_student )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_student_classroom_subject_classroom_module1
    FOREIGN KEY (classroom_module_idclassroom_module )
    REFERENCES rpgm.classroom_module (idclassroom_module )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.staff_has_role
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.staff_has_role ;

CREATE  TABLE IF NOT EXISTS rpgm.staff_has_role (
  idstaff_has_role INT NOT NULL AUTO_INCREMENT ,
  staff_idstaff INT NOT NULL ,
  role_idrole INT NOT NULL ,
  start_date DATE NOT NULL ,
  end_date DATE NULL ,
  PRIMARY KEY (idstaff_has_role) ,
  INDEX fk_staff_has_role_role1_idx (role_idrole ASC) ,
  INDEX fk_staff_has_role_staff1_idx (staff_idstaff ASC) ,
  CONSTRAINT fk_staff_has_role_staff1
    FOREIGN KEY (staff_idstaff )
    REFERENCES rpgm.staff (idstaff )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_staff_has_role_role1
    FOREIGN KEY (role_idrole )
    REFERENCES rpgm.role (idrole )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.school_has_staff_has_role
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.school_has_staff_has_role ;

CREATE  TABLE IF NOT EXISTS rpgm.school_has_staff_has_role (
  school_idschool INT NOT NULL ,
  staff_has_role_idstaff_has_role INT NOT NULL ,
  PRIMARY KEY (school_idschool, staff_has_role_idstaff_has_role) ,
  INDEX fk_school_has_staff_has_role_staff_has_role1_idx (staff_has_role_idstaff_has_role ASC) ,
  INDEX fk_school_has_staff_has_role_school1_idx (school_idschool ASC) ,
  CONSTRAINT fk_school_has_staff_has_role_school1
    FOREIGN KEY (school_idschool )
    REFERENCES rpgm.school (idschool )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_school_has_staff_has_role_staff_has_role1
    FOREIGN KEY (staff_has_role_idstaff_has_role )
    REFERENCES rpgm.staff_has_role (idstaff_has_role )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.section_has_staff_has_role
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.section_has_staff_has_role ;

CREATE  TABLE IF NOT EXISTS rpgm.section_has_staff_has_role (
  section_idsection INT NOT NULL ,
  staff_has_role_idstaff_has_role INT NOT NULL ,
  PRIMARY KEY (section_idsection, staff_has_role_idstaff_has_role) ,
  INDEX fk_section_has_staff_has_role_staff_has_role1_idx (staff_has_role_idstaff_has_role ASC) ,
  INDEX fk_section_has_staff_has_role_section1_idx (section_idsection ASC) ,
  CONSTRAINT fk_section_has_staff_has_role_section1
    FOREIGN KEY (section_idsection )
    REFERENCES rpgm.section (idsection )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_section_has_staff_has_role_staff_has_role1
    FOREIGN KEY (staff_has_role_idstaff_has_role )
    REFERENCES rpgm.staff_has_role (idstaff_has_role )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.classroom_has_staff_has_role
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.classroom_has_staff_has_role ;

CREATE  TABLE IF NOT EXISTS rpgm.classroom_has_staff_has_role (
  staff_has_role_idstaff_has_role INT NOT NULL ,
  classroom_idclassroom INT NOT NULL ,
  PRIMARY KEY (staff_has_role_idstaff_has_role, classroom_idclassroom) ,
  INDEX fk_classroom_has_staff_has_role_staff_has_role1_idx (staff_has_role_idstaff_has_role ASC) ,
  INDEX fk_classroom_has_staff_has_role_classroom1_idx (classroom_idclassroom ASC) ,
  CONSTRAINT fk_classroom_has_staff_has_role_staff_has_role1
    FOREIGN KEY (staff_has_role_idstaff_has_role )
    REFERENCES rpgm.staff_has_role (idstaff_has_role )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_classroom_has_staff_has_role_classroom1
    FOREIGN KEY (classroom_idclassroom )
    REFERENCES rpgm.classroom (idclassroom )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.student_generalexam_profile
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.student_generalexam_profile ;

CREATE  TABLE IF NOT EXISTS rpgm.student_generalexam_profile (
  idstudent_generalexam_profile INT NOT NULL AUTO_INCREMENT ,
  student_idstudent INT NOT NULL ,
  al_island_rank INT NULL ,
  zscore FLOAT NULL ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (idstudent_generalexam_profile) ,
  INDEX fk_student_generalexam_profile_student1_idx (student_idstudent ASC) ,
  CONSTRAINT fk_student_generalexam_profile_student1
    FOREIGN KEY (student_idstudent )
    REFERENCES rpgm.student (idstudent )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.exam_sync
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.exam_sync ;

CREATE  TABLE IF NOT EXISTS rpgm.exam_sync (
  idexam_sync INT NOT NULL AUTO_INCREMENT ,
  exam_idexam INT NOT NULL ,
  class_idexam INT NOT NULL DEFAULT 0 ,
  sync_status INT NOT NULL DEFAULT 0 ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (idexam_sync) ,
  INDEX fk_exam_sync_exam1_idx (exam_idexam ASC) ,
  UNIQUE INDEX exam_idexam_UNIQUE (exam_idexam ASC) ,
  CONSTRAINT fk_exam_sync_exam1
    FOREIGN KEY (exam_idexam )
    REFERENCES rpgm.exam (idexam )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.student_sync
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.student_sync ;

CREATE  TABLE IF NOT EXISTS rpgm.student_sync (
  idstudent_sync INT NOT NULL AUTO_INCREMENT ,
  student_idstudent INT NOT NULL ,
  class_idstudent INT NOT NULL DEFAULT 0 ,
  sync_status INT NOT NULL DEFAULT 0 ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (idstudent_sync) ,
  INDEX fk_student_sync_student1_idx (student_idstudent ASC) ,
  UNIQUE INDEX student_idstudent_UNIQUE (student_idstudent ASC) ,
  CONSTRAINT fk_student_sync_student1
    FOREIGN KEY (student_idstudent )
    REFERENCES rpgm.student (idstudent )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.class_analyzer_classifier
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.class_analyzer_classifier ;

CREATE  TABLE IF NOT EXISTS rpgm.class_analyzer_classifier (
  idclass_analyzer_classifier INT NOT NULL ,
  year INT NOT NULL ,
  grade INT NOT NULL ,
  term INT NOT NULL ,
  subject VARCHAR(100) NOT NULL ,
  model LONGBLOB NOT NULL ,
  type VARCHAR(500) NOT NULL ,
  bin INT NOT NULL ,
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (idclass_analyzer_classifier) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table rpgm.classroom_module_has_staff_has_role
-- -----------------------------------------------------
DROP TABLE IF EXISTS rpgm.classroom_module_has_staff_has_role ;

CREATE  TABLE IF NOT EXISTS rpgm.classroom_module_has_staff_has_role (
  classroom_module_idclassroom_module INT NOT NULL ,
  staff_has_role_idstaff_has_role INT NOT NULL ,
  PRIMARY KEY (classroom_module_idclassroom_module, staff_has_role_idstaff_has_role) ,
  INDEX fk_classroom_module_has_staff_has_role_staff_has_role1_idx (staff_has_role_idstaff_has_role ASC) ,
  INDEX fk_classroom_module_has_staff_has_role_classroom_module1_idx (classroom_module_idclassroom_module ASC) ,
  CONSTRAINT fk_classroom_module_has_staff_has_role_classroom_module1
    FOREIGN KEY (classroom_module_idclassroom_module )
    REFERENCES rpgm.classroom_module (idclassroom_module )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_classroom_module_has_staff_has_role_staff_has_role1
    FOREIGN KEY (staff_has_role_idstaff_has_role )
    REFERENCES rpgm.staff_has_role (idstaff_has_role )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE rpgm;

SET SQL_MODE =' ';
GRANT USAGE ON *.* TO rpgm_user;
 DROP USER rpgm_user;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'rpgm_user' IDENTIFIED BY 'rpgm@123';
-- Select * From staff;
GRANT ALL ON rpgm.* TO 'rpgm_user';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table rpgm.exam_type
-- -----------------------------------------------------
START TRANSACTION;
USE rpgm;
INSERT INTO rpgm.exam_type (idexam_type, type_name) VALUES (1, 'CONTINUOUS ASSESSMENT');
INSERT INTO rpgm.exam_type (idexam_type, type_name) VALUES (2, 'TERM EXAM');
INSERT INTO rpgm.exam_type (idexam_type, type_name) VALUES (3, 'GENERAL EXAM');

COMMIT;

-- -----------------------------------------------------
-- Data for table rpgm.user_role
-- -----------------------------------------------------
START TRANSACTION;
USE rpgm;
INSERT INTO rpgm.user_role (iduser_role, name) VALUES (1, 'admin');
INSERT INTO rpgm.user_role (iduser_role, name) VALUES (2, 'manager');

COMMIT;
