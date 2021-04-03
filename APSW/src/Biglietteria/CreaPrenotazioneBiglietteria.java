package Biglietteria;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.MetodoPagamento;
import Beans.Posto;
import Beans.Prenotazione;
import Beans.Turno;
import Beans.Utente;
import Utils.Utils;

/**
 * Servlet implementation class CreaPrenotazioneBiglietteria
 */
@WebServlet("/biglietteria/creaPrenotazioneBiglietteria")
public class CreaPrenotazioneBiglietteria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaPrenotazioneBiglietteria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date data = Date.valueOf(request.getParameter("data"));
		
		String fasciaOraria = request.getParameter("turno");
		Posto posto = new Posto(Integer.parseInt(request.getParameter("codPosto")));
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		
		MetodoPagamento metodoPagamento;
		
		String isContanti = request.getParameter("isContanti");
		String numeroCarta = request.getParameter("numeroCarta");
		
		if (isContanti != null)
			metodoPagamento = new MetodoPagamento("Contanti");
		else 
			metodoPagamento = new MetodoPagamento(numeroCarta);
		
		Turno turno = Turno.getTurno(fasciaOraria);
		Prenotazione nuovaPrenotazione = new Prenotazione(posto, data, turno, metodoPagamento, nome, cognome, true, posto.getCostoTotale());
				
		/*
		 * Le prenotazioni create dalla biglietteria vengono associate all'account ospite 
		 */
		
		Utente utente = Utente.getUtenteFromDB("none");
		boolean success = utente.addPrenotazione(nuovaPrenotazione);
		
		if (success)
			response.sendRedirect("index");
		else {
			String errorText = "Si è verificato un errore durante la creazione di una nuova prenotazione. Riprovare.";
			String previousPage = "newPrenotazioneBiglietteria";
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
