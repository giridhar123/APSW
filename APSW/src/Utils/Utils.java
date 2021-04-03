package Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Utente;

public abstract class Utils {

	public static Utente getLoggedInUtente(HttpServletRequest request) {
		String emailLoggedIn = request.getRemoteUser();

		Utente utente = null;
		if (emailLoggedIn != null)
			utente = Utente.getUtenteFromDB(emailLoggedIn);
		
		return utente;
	}
	
	public static void forwardToAdvisePage(HttpServletRequest request, HttpServletResponse response, String adviseText, String previousPage) throws ServletException, IOException {
		request.setAttribute("errorText", adviseText);
		request.setAttribute("previousPage", previousPage);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/advisePage.jsp");
		dispatcher.forward(request, response);
	}
	
	public static void sendEmail(String destinatario, String soggetto, String messaggio){  
        MailSender mailSender = new MailSender(soggetto, messaggio, destinatario);
        mailSender.start();
	}  
	
	public static String generateRandomAlphanumericString(int len) {
		/*
		 * Credits:
		 * https://www.baeldung.com/java-random-string
		 */
		
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    Random random = new Random();
	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(len)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    
	    return generatedString;
	}
	
	public static String getSha256(String data) {
		/*
		 * Credits:
		 * https://www.baeldung.com/sha-256-hashing-java
		 */
		
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(
					  data.getBytes(StandardCharsets.UTF_8));
					
			StringBuffer hexString = new StringBuffer();
		    for (int i = 0; i < encodedhash.length; i++) {
		    	String hex = Integer.toHexString(0xff & encodedhash[i]);
		    	if(hex.length() == 1) hexString.append('0');
		        	hexString.append(hex);
		    }
		    return hexString.toString();
		    
		} catch (NoSuchAlgorithmException e) {
			return null;
		}		
	}
}
