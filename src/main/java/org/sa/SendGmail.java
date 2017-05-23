package org.sa;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendGmail 
{
		
	private Session session;
	
	
	public SendGmail(final String username, final String pass)
	{		
		Properties p = new Properties();
		
		//	GMail properties
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "587");
		
		this.session = Session.getInstance(p, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(username, pass);
			}
		});
	}
	
	public boolean sendMessage(String from, InternetAddress[] toArray, String subject, String message)
	{
		Message m = new MimeMessage(this.session);
		
		try 
		{
			m.setFrom(new InternetAddress(from));
			m.setRecipients(Message.RecipientType.TO, toArray);
			m.setSubject(subject);
			m.setText(message);
			
			System.out.println("Sending the message...");
			Transport.send(m);
			System.out.println("Message sent.");
		} 
		catch (AddressException e) {
			e.printStackTrace();
		} 
		catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
		
		return false;
	}
	
}
