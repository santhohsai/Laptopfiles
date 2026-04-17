package com.student.service;

import java.util.List;

import com.student.entity.Student;

public interface StudentService {
	
	String registration(Student studentdata);
	
	String updatedetails(Student studentdatabyid);
	
	List<Student> getstudentdata();
	
	//Student  signin(Student stdsignin);
	
	

}
