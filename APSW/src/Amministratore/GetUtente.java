package Amministratore;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Utente;

/**
 * Servlet implementation class getUtente
 */
@WebServlet("/amministratore/getUtente")
public class GetUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		StringBuilder json = new StringBuilder();
		json.append("{");
		Utente utente = Utente.getUtenteFromDB(email);
		if (utente != null) {
			json.append("\"success\": true, ");
			json.append("\"nome\": \"" + utente.getNome() + "\", ");
			json.append("\"cognome\": \"" + utente.getCognome() + "\", ");
			json.append("\"email\": \"" + utente.getEmail() + "\", ");
			json.append("\"idPermesso\": " + utente.getIdPermesso() + "");
		}
		else
			json.append("\"success\": false ");
		
		json.append("}");
		
		response.getWriter().append(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
