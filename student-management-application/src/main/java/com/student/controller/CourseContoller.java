package com.student.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Course;
import com.student.repo.Courserepo;
import com.student.service.CourseService;

@RestController("/api/v2")
public class CourseContoller 
{
	@Autowired
	CourseService courseservice;
	
  @PostMapping("/addcourse")
  public ResponseEntity<String> registercourse(@RequestBody Course  course)
  {
	 try
	 {
		 String  addcourse = courseservice.registercourse(course);
		 
		 return ResponseEntity.status(HttpStatus.CREATED).body(addcourse);
	 }
	 catch(Exception e)
	 {
		 
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("course already exists");
	 }
	  
  }
  
  @GetMapping("/getdetails")
  public ResponseEntity<List<Course>> getcoursedetails()
  {
	 
		 List<Course> coursedetails = courseservice.getcoursedetails();
		 return  ResponseEntity.status(HttpStatus.OK).body(coursedetails);

  }
  
  @PutMapping("/update")
  public  ResponseEntity<?> updatecourse(@RequestBody Course edit)
  {
	  try 
	  {
           Optional<Course>  updatedcourse = courseservice.updatecourse(edit);

           
           return ResponseEntity.status(HttpStatus.OK).body(updatedcourse);
		}
	  catch(Exception e)
	  {
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occured"+e.getMessage());
	  }

   }
  
  @DeleteMapping("/delete")
  public ResponseEntity<String> deletecourse(@RequestBody Course course)
  {
	  try {
	  String deletedcourse = courseservice.deletecourse(course);
	  return ResponseEntity.status(HttpStatus.OK).body("deleted"+ deletedcourse);
	  }
	  catch (Exception e)
	  {
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("an error occured while deleting : "+e.getMessage());
	  }
  }
}
