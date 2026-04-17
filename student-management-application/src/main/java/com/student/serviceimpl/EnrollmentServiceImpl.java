package com.student.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.entity.Course;
import com.student.entity.Enrollment;
import com.student.entity.Student;
import com.student.repo.Courserepo;
import com.student.repo.Enrollrepo;
import com.student.repo.Studentrepo;
import com.student.service.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService{
	
	@Autowired
	Courserepo courserepo;
	
	@Autowired
	Studentrepo studentrepo;
	
	@Autowired
	Enrollrepo enrollrepo;
	
	@Autowired
    EmailService emailservice;

	@Override
	public String enrollstudentstocourse(String studentId, Long courseId,String grade) {
	
		Course course = courserepo.findById(courseId)
		     .orElseThrow(() -> new RuntimeException("course not found"));
		
		Student student = studentrepo.findById(studentId)
				.orElseThrow(() -> new RuntimeException("studnet not found"));
		
		 Enrollment enrollment = new Enrollment(student,course);

          enrollment.setEnrollmentDate(LocalDate.now()); // Set current date
          enrollment.setGrade("A");
		   enrollrepo.save(enrollment);
		   
		   emailservice.sendeconfirmationemail(student.getFirstName(), student.getLastName(),student.getEmail(),course.getCourseName());
		   

	        return "Student enrolled in course successfully.";
	    }

	@Override
	public List<Enrollment> getAllEnrollments() {
		
		return enrollrepo.findAll();
	}
	
	
	




}
