package Cliente.OrdineBar;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.DettagliOrdine;
import Beans.Ordine;
import Beans.Utente;
import Utils.Utils;

/**
 * Servlet implementation class dettagliOrdine
 */
@WebServlet("/cliente/dettagliOrdine")
public class DettagliOrdineBar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettagliOrdineBar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = Utils.getLoggedInUtente(request);
		int codOrdine = Integer.parseInt(request.getParameter("codOrdine"));
		Ordine ordine = Ordine.getOrdine(codOrdine);
		List<DettagliOrdine> dettagli = DettagliOrdine.getDettagli(codOrdine);
		request.setAttribute("loggedUser", utente);
		request.setAttribute("dettagli", dettagli);
		request.setAttribute("ordine", ordine);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/dettagliOrdine.jsp");
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
