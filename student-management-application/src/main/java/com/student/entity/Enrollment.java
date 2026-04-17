
package com.student.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Enrollment 
{
	@Id
	@GeneratedValue
	 private int enrollId;
	
	@ManyToOne
	@JoinColumn(name = "student_id" ,nullable = false)
	private Student student;
	
	@ManyToOne
	@JoinColumn(name ="course_id" , nullable = false)
	private Course course;
	private LocalDate enrollmentDate;
	private String grade;
	
	
	
	public Enrollment(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
	}

	public Enrollment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getEnrollId() {
		return enrollId;
	}
	public void setEnrollId(int enrollId) {
		this.enrollId = enrollId;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	   
	
}
