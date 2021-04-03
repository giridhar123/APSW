package Utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender extends Thread {
	
	/*
	 * Senza thread il browser si blocca finchè la mail non viene inviata.
	 */
	
	private final static String EMail = "lavallesiragusalido@gmail.com";
	private final static String EMailPassword = "hehehehe ;-)";
	
	private String soggetto;
	private String messaggio;
	private String destinatario;
	
	public MailSender(String soggetto, String messaggio, String destinatario) {
		this.soggetto = soggetto;
		this.messaggio = messaggio;
		this.destinatario = destinatario;
	}
	
	public void run() {
		//Get properties object    
        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        
        //get Session   
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {    
        		protected PasswordAuthentication getPasswordAuthentication() {    
        			return new PasswordAuthentication(EMail,EMailPassword);  
        		}    
        	});    
        //compose message    
        try {    
        	MimeMessage message = new MimeMessage(session);    
        	message.addRecipient(Message.RecipientType.TO,new InternetAddress(destinatario));    
        	message.setSubject(soggetto);    
        	message.setText(messaggio);    
        	
        	//send message  
        	Transport.send(message);    
        } catch (MessagingException e) {
        	throw new RuntimeException(e);
        }  
	}

}
