package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Student;
import com.student.service.StudentService;



@RestController("/api/v1")
public class StudentController 
{
	@Autowired
	StudentService studentservice;
	
	@PostMapping("/create")
	public ResponseEntity<String> registration(@RequestBody Student studentdata)
	{ 
		try
		{
			String student =  studentservice.registration(studentdata);
			
			return  ResponseEntity.status(HttpStatus.CREATED).body(student);
		}
		catch(Exception e)
		{
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("student already registered");
		}
		
	}
	
	@PutMapping("/EdIt")
	public ResponseEntity<String> updatedetails(@RequestBody Student stdata)
	{
		try
		{  
			String studentdata = studentservice.updatedetails(stdata);
			
			return ResponseEntity.status(HttpStatus.OK).body(studentdata);
		}
		catch(Exception e)
		{
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unable to update details");
		}
	}
	
	@GetMapping("/getstudent")
	public ResponseEntity<List<Student>> getstudentdata()
	{
		List<Student> stud = studentservice.getstudentdata();
		
        return new ResponseEntity<List<Student>>(stud,HttpStatus.ACCEPTED);
	}
	
/*	@PostMapping("/login")
	public ResponseEntity<Student> signin(@RequestBody Student signin)
	{
		 Student login = studentservice.signin(signin);
		 
		 return ResponseEntity.status(HttpStatus.ACCEPTED).body(login);
		
	}*/

}
