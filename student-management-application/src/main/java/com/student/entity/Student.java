package com.student.entity;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
public class Student 
{
	
	@Id
	private String id;
	@PrePersist
    private void generateId() {
        if (this.id == null||this.id =="") {
            Random random = new Random();
            int randomNumber = random.nextInt(999999); 
            DecimalFormat df = new DecimalFormat("000000");
            this.id = "000" + df.format(randomNumber);
        }
    }
	
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String password;
	    private LocalDate dateOfBirth;
	    private String address;
	    private String phoneNumber;
	    
	
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public LocalDate getDateOfBirth() {
			return dateOfBirth;
		}
		public void setDateOfBirth(LocalDate dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	

}
