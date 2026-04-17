package com.student.service;

import java.util.List;

import com.student.entity.Enrollment;

public interface EnrollmentService 
{
	String enrollstudentstocourse(String studentId, Long courseId,String grade);
	
	List<Enrollment> getAllEnrollments();
	
	
    
}
