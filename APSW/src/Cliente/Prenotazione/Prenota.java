package Cliente.Prenotazione;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Prenotazione;
import Beans.Utente;
import Utils.Utils;

/**
 * Servlet implementation class Prenota
 */
@WebServlet("/cliente/prenota")
public class Prenota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prenota() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = Utils.getLoggedInUtente(request);
		List<Prenotazione> listaPrenotazioni = utente.getPrenotazioni();
		
		request.setAttribute("listaPrenotazioni", listaPrenotazioni);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/prenotazioni.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
