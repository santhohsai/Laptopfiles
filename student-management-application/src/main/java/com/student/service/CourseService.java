package com.student.service;

import java.util.List;
import java.util.Optional;

import com.student.entity.Course;

public interface CourseService {
	
	String registercourse(Course course);
	
	List<Course> getcoursedetails();
	
	Optional<Course> updatecourse(Course course);
	
	String deletecourse(Course course);

}
