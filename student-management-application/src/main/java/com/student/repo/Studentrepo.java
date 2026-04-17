package com.student.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.entity.Student;


@Repository
public interface Studentrepo extends JpaRepository<Student, String>
{
   	
  Optional<Student>  findByEmail(String email);
  
   Optional<Student> findById(String id); 
  
  //Optional<Student>   findByEmailandPassword(String email, String  password);
  
}
