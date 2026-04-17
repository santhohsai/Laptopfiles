package com.student.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.entity.Course;
import com.student.repo.Courserepo;
import com.student.service.CourseService;

@Service
public class CourseServiceimpl  implements CourseService{
	
	@Autowired
	Courserepo courserepo;

	@Override
	public String registercourse(Course course) {
		
		Optional<Course> coursedata = courserepo.findById(course.getId());
		
		if(coursedata.isPresent())
		{
			return "course already present";
		}
		else
		{
			 courserepo.save(course);
			
			 return "course registered successfully";
		}
	}

	@Override
	public List<Course> getcoursedetails() {
	  List<Course> course = courserepo.findAll();
	  
	  if(course.isEmpty())
	  {
		  throw new RuntimeException(" courses not found");
	  }
	  else
	  {
		  return course;
	  }
	}

	@Override
	public Optional<Course> updatecourse(Course course) {
        
		Optional<Course> editcourse = courserepo.findById(course.getId());
		
		if(editcourse.isPresent())
		{
			editcourse.get().setCourseName(course.getCourseName());
			editcourse.get().setCourseCode(course.getCourseCode());
			editcourse.get().setCredits(course.getCredits());
			editcourse.get().setDescription(course.getDescription());
			
			courserepo.save(editcourse.get());
			
			return editcourse;
		}
		else
		{
		throw new RuntimeException("course not found");
		}
	
	}

	@Override
	public String deletecourse(Course course) {
		
	  Optional<Course>  existedcourse = courserepo.findById(course.getId());
	  if(existedcourse.isPresent())
	  {
		  courserepo.delete(course);
		  return "course deleted successfully";
		  
	  }
	  else
	  {
		  return "course does not exist";
	  }
	
	}

}
