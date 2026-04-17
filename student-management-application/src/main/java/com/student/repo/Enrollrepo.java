package com.student.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.entity.Enrollment;

@Repository
public interface Enrollrepo extends JpaRepository<Enrollment, Integer>
{
    
}
