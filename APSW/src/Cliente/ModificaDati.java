package Cliente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Utente;
import Utils.Utils;

/**
 * Servlet implementation class ModificaDati
 */
@WebServlet("/cliente/modificaDati")
public class ModificaDati extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaDati() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		
		if (cmd == null) {
			/*
			 * Forward alla jsp
			 */
			
			Utente utente =  Utils.getLoggedInUtente(request);
			request.setAttribute("utente", utente);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/modificaDati.jsp");
			dispatcher.forward(request, response);
		}
		else {
			boolean success = false;
			
			if (cmd.equals("checkEmail")) {
				/*
				 * Controllo se esiste un account con l'e-mail inserita dall'utente
				 */
				
				String email = request.getParameter("email");
				Utente utenteLoggato = Utils.getLoggedInUtente(request);
				
				if (email.equals(utenteLoggato.getEmail()))
					success = true;
				else {
					Utente utente = Utente.getUtenteFromDB(email);
	
					if (utente == null)
						success = true;
				}
			}
			else if (cmd.equals("checkPassword")) {
				/*
				 * Controllo se la password corrente inserita dall'utente è corretta
				 */
				
				String password = request.getParameter("password");
				Utente utenteLoggato = Utils.getLoggedInUtente(request);
				
				if (password.equals(utenteLoggato.getPassword()))
					success = true;
			}
			else if (cmd.equals("submit")) {
				/*
				 * Submit del form e modifica dei dati dell'utente 
				 */
				
				String nome = request.getParameter("nome");
				String cognome = request.getParameter("cognome");
				String newEmail = request.getParameter("email");
				String currentPassword = request.getParameter("currentPassword");
				String newPassword = request.getParameter("newPassword");
				
				Utente utenteLogged = Utils.getLoggedInUtente(request);
				String currentEmail = utenteLogged.getEmail();
				success = Utente.updateUtente(currentEmail, newEmail, nome, cognome, currentPassword, newPassword);
				request.logout();
			}
			
			String json = "{\"success\": " + success + "}";
			response.getWriter().append(json);
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
