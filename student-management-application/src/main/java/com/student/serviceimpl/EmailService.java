package com.student.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService 
{

	@Autowired
	private  JavaMailSender  mailsender;
	
	
	
	public void sendeconfirmationemail(String firstName, String lastName,String email, String courseName) {
	
		SimpleMailMessage messsage = new  SimpleMailMessage();
		messsage.setTo(email);
		messsage.setText("Dear "+firstName+lastName+",\n\n" + 
		               
		     "you have suuceefully enrolled for the course "+ courseName + "\n\n" +
		     
			 "Thank you for registering the course! \n\nRegards , \n Santhosh sai Institution");
		               
		messsage.setSubject("course registeration confirmation");
		
		messsage.setFrom("visweswarabonda@gmail.com");
		
		mailsender.send(messsage);
		
	}
	
	

}
