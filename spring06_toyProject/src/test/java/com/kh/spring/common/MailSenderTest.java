package com.kh.spring.common;


import java.util.Date;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MailSenderTest {
	
	   @Autowired
	   JavaMailSenderImpl mailSender;
	   
	   @Test
	   public void sendEmail() throws Exception {
		   
		   MimeMessage msg =  mailSender.createMimeMessage();
		   msg.setFrom("oookylt@gmail.com");
	       msg.setRecipients(Message.RecipientType.TO,"oooklyt@gmail.com");
	       msg.setSubject("메일테스트");
	       msg.setSentDate(new Date());
	       msg.setText("<h1>Email Test</h1>","UTF-8","html");
	       mailSender.send(msg);
	   }

}
