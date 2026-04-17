package com.student.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.entity.Student;
import com.student.repo.Studentrepo;
import com.student.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService
{
   @Autowired
   Studentrepo studentrepo;
	@Override
	public String registration(Student studentdata) {
		Optional<Student> studentsreg = studentrepo.findByEmail(studentdata.getEmail());
		if(studentsreg.isPresent())
		{
			return "student with this email already exists";
		}
		else
		{
			studentrepo.save(studentdata);
			return "studnet registered successfully";
		}
		}

	@Override
	public String updatedetails(Student studentdatabyid) {

		
		Optional<Student>  studentupdation = studentrepo.findById(studentdatabyid.getId());
		if(studentupdation.isPresent())
		{
			studentupdation.get().setFirstName(studentdatabyid.getFirstName());
			studentupdation.get().setLastName(studentdatabyid.getLastName());
			studentupdation.get().setAddress(studentdatabyid.getAddress());
			studentupdation.get().setDateOfBirth(studentdatabyid.getDateOfBirth());
			studentupdation.get().setEmail(studentdatabyid.getEmail());
			studentupdation.get().setPassword(studentdatabyid.getPassword());
			studentupdation.get().setPhoneNumber(studentdatabyid.getPhoneNumber());
			
			studentrepo.save(studentupdation.get());
			return  "student details updated suuessfully";
			
		}
	    throw new RuntimeException("student does not exists");
	
	}

	@Override
	public List<Student> getstudentdata() 
	{
		return studentrepo.findAll();
	}

	/*@Override
	public Student signin(Student stdsignin) {
	   
		Optional<Student> login = studentrepo.findByEmailandPassword(stdsignin.getEmail(), stdsignin.getPassword());
		if(login.isPresent())
		{
			Student std = login.get() ;
			 return std;
		}
		else
		{
			throw new RuntimeException("login fail");
		}
		

	}*/
	
	

}
