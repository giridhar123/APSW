package Cliente.Prenotazione;

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
 * Servlet implementation class NewPrenotazione
 */
@WebServlet("/cliente/creaPrenotazione")
public class CreaPrenotazioneCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaPrenotazioneCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		String sData = request.getParameter("data");
		String fasciaOraria = request.getParameter("turno");
		int codPosto = Integer.parseInt(request.getParameter("codPosto"));
		String numeroCarta = request.getParameter("numeroCarta");
		
		Utente utente = Utils.getLoggedInUtente(request);
		Date data = Date.valueOf(sData);
		
		Turno turno = Turno.getTurno(fasciaOraria);
		MetodoPagamento metodoPagamento = new MetodoPagamento(numeroCarta);
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		Posto posto = new Posto(codPosto);
		
		boolean pagato = true; //Se il cliente paga con la carta, la prenotazione risulta già pagata
		if (metodoPagamento.isContanti())
			pagato = false;
		
		Prenotazione nuovaPrenotazione = new Prenotazione(posto, data, turno, metodoPagamento, nome, cognome, pagato, posto.getCostoTotale());
		
		boolean success = utente.addPrenotazione(nuovaPrenotazione);
				
		if (success)
			response.sendRedirect("prenota");
		else {
			String errorText = "Si è verificato un errore durante la creazione di una nuova prenotazione. Riprovare.";
			String previousPage = "newPrenotazione";
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
