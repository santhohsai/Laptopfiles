package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Enrollment;
import com.student.service.EnrollmentService;

@RestController("api/v3")
public class EnrollController {
	
	@Autowired
	EnrollmentService enrollservice;
	
	@PostMapping("/enroll")
	 public ResponseEntity<String> enrollStudentToCourse(@RequestParam String studentId, @RequestParam Long courseId,@RequestParam(required = false)String grade) {
        try {
        	if(grade == null)
        	{
        		grade = "A";
        	}
		String result = enrollservice.enrollstudentstocourse(studentId, courseId,grade);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        catch(Exception e)
        {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
	
	 @GetMapping("/enrollstudentwithcourses")
	    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
	        List<Enrollment> enrollments = enrollservice.getAllEnrollments();
	        return ResponseEntity.ok(enrollments);
	    }

}
