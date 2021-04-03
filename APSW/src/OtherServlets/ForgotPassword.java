package OtherServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Utente;
import Utils.Utils;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String newPassword = Utils.generateRandomAlphanumericString(10);

		boolean success = Utente.changePassword(email, newPassword);
		
		String adviseText = "";
		String previousPage = "./index.jsp";
		if (success) {
			String soggetto = "Password modificata presso LavalleSiragusa Lido";
			String messaggio = "Hai richiesto il reset della password.\n"
							+ "La tua nuova password è " + newPassword + ".\n"
							+ "Sei pregato di modificarla al più presto.";
			
			Utils.sendEmail(email, soggetto, messaggio);
			
			adviseText = "Password cambiata correttamente.\n La tua nuova password è: " + newPassword + ".\n"
					+ "Inoltre, riceverei una e-mail con la nuova password.";
		}
		else
			adviseText = "Si è verificato un errore durante il recupero password, riprovare.";
		
		Utils.forwardToAdvisePage(request, response, adviseText, previousPage);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
