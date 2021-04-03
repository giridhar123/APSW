package Cassiere;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.DettagliOrdine;
import Beans.Ordine;
import Beans.Utente;
import Utils.Carrello;
import Utils.Utils;

/**
 * Servlet implementation class creaOrdineCassiere
 */
@WebServlet("/cassiere/creaOrdineBar")
public class CreaOrdineBarCassiere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaOrdineBarCassiere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
		String numeroCarta;
		if(request.getParameter("contanti")==null) {
			numeroCarta = request.getParameter("numeroCarta");
		}else {
			numeroCarta = "Contanti";
		}
		
		Utente utente = Utente.getUtenteFromDB("none");
		Ordine ordine= new Ordine(numeroCarta, 1);
		int codiceOrdine = utente.addOrdine(ordine);
		int codiceUtente = utente.getCodUtente();
		if(codiceOrdine!=-1) {
			DettagliOrdine.addDettagli(carrello, codiceOrdine, codiceUtente);
			request.getSession().setAttribute("carrello", null);
			response.sendRedirect("index");
		}else {
			String errorText = "Si è verificato un errore durante la creazione di un nuovo oridine. Riprovare.";
			String previousPage = "newOrdineBar";
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
