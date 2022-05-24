package com.apiAP.app.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


public class MailSender {
	

	public static void sendMail(String to, String subjet, String text) throws AddressException, MessagingException {
		
	
	
		
		Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.user", "borlenghigian");
	    props.put("mail.smtp.password", "qxdcxutjckutoppw");    
	    props.put("mail.smtp.auth", "true");    
	    props.put("mail.smtp.starttls.enable", "true"); 
	    props.put("mail.smtp.port", "587"); 
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	   
	    

		
	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    message.setFrom(new InternetAddress("borlenghigian"));
        message.addRecipients(Message.RecipientType.TO, to); 
        message.setSubject(subjet);
        message.setText(text);
        Transport transport = session.getTransport("smtp");
  
			transport.connect("smtp.gmail.com", "borlenghigian", "qxdcxutjckutoppw");
		
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        
	}
	
	public static String mailBody(String mail) {
		String text = "Hello dear."+"\nThanks for register in my Web."+
	"\n\nYour username will be your mail: ' "+mail+" ' "+
				"\nIf you forgot your password, go to 'Forgot Password' in the login section and it will be reset in an email in your inbox."+
	"\n\nThanks for all, hope you enjoy it."+
				"\n\nBye dear.";
		return text;
}
    
}
