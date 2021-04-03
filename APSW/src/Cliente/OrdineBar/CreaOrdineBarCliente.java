package Cliente.OrdineBar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.DettagliOrdine;
import Beans.Ordine;
import Beans.Utente;
import Utils.Utils;
import Utils.Carrello;

/**
 * Servlet implementation class newOrdineBar
 */
@WebServlet("/cliente/creaOrdineBar")
public class CreaOrdineBarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaOrdineBarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
		String numeroCarta = request.getParameter("numeroCarta");
		Utente utente = Utils.getLoggedInUtente(request);
		Ordine ordine= new Ordine(numeroCarta);
		int codiceOrdine = utente.addOrdine(ordine);
		int codiceUtente = utente.getCodUtente();
		if(codiceOrdine!=-1) {
			DettagliOrdine.addDettagli(carrello, codiceOrdine, codiceUtente);
			request.getSession().setAttribute("carrello", null);
			response.sendRedirect("ordini");
		}else {
			String errorText = "Si è verificato un errore durante la creazione di un nuovo oridine. Riprovare.";
			String previousPage = "newOrdine.jsp";
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
