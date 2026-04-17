package com.student.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {
	
	  @Id
	    private Long id;

	    private String courseName;
	    private String courseCode;
	    private String description;
	    private int credits;
	    
	    
		
		public String getCourseName() {
			return courseName;
		}
		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}
		public String getCourseCode() {
			return courseCode;
		}
		public void setCourseCode(String courseCode) {
			this.courseCode = courseCode;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public int getCredits() {
			return credits;
		}
		public void setCredits(int credits) {
			this.credits = credits;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}

}
