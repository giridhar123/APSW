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
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		
		Utente utente = new Utente(nome, cognome, email);
		if (utente.registraNelDB(request.getParameter("password"))) {
			/*
			 * Forward alla pagina di avviso
			 */
			String errorText = "Registrazione avvenuta con successo.";
			String previousPage = "index.jsp";
			Utils.forwardToAdvisePage(request, response, errorText, previousPage);
			
			/*
			 * E-Mail di registrazione
			 */
			String soggetto = "Registrazione presso LavalleSiragusa Lido";
			String messaggio = "Ciao " + nome + " " + cognome + ". La registrazione presso LavalleSiragusa Lido è avvenuta con successo.";
			Utils.sendEmail(email, soggetto, messaggio);
		}
		else {
			String errorText = "Si è verificato un errore durante la registrazione.\nRiprovare.";
			String previousPage = "index.jsp";
			Utils.forwardToAdvisePage(request, response, errorText, previousPage);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
