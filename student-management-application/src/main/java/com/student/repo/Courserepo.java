package com.student.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.entity.Course;

@Repository
public interface Courserepo extends JpaRepository<Course, Long>
{
	Course findById(Course id);
	

}
