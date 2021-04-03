package Cliente.Prenotazione;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.MetodoPagamento;
import Beans.Posto;
import Beans.Utente;
import Utils.Utils;

/**
 * Servlet implementation class NewPrenotazione
 */
@WebServlet("/cliente/newPrenotazione")
public class NewPrenotazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPrenotazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = Utils.getLoggedInUtente(request);
		List<MetodoPagamento> metodiPagamento = utente.getMetodiPagamento();
		List<Posto> listaPosti = Posto.getAll();
		
		request.setAttribute("utente", utente);
		request.setAttribute("metodiPagamento", metodiPagamento);
		request.setAttribute("listaPosti", listaPosti);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/newPrenotazione.jsp");
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
