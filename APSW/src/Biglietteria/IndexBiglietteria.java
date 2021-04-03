package Biglietteria;

import java.io.IOException;
import java.sql.Date;
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
 * Servlet implementation class Biglietteria
 */
@WebServlet("/biglietteria/index")
public class IndexBiglietteria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexBiglietteria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sData = request.getParameter("data");
		List<Prenotazione> listaPrenotazioni = null;
		
		Date data;
		if (sData == null || sData.equals("")) {
			//L'utente ha appena aperto la pagina quindi mostro le prenotazioni della data odierna
			data = new Date(new java.util.Date().getTime());
			sData = data.toString();
		}
		else
			data = Date.valueOf(sData);		
		
		listaPrenotazioni = Prenotazione.getPrenotazioni(data);
		
		Utente utente = Utils.getLoggedInUtente(request);
		request.setAttribute("utente", utente);
		request.setAttribute("listaPrenotazioni", listaPrenotazioni);
		
		/*
		 * Modifiche alla data: da yyyy/mm/dd a dd/mm/yyyy
		 */
		String[] ymd = sData.split("-");
		String newData = ymd[2] + "/" + ymd[1] + "/" + ymd[0];
		
		request.setAttribute("data", newData);
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("/biglietteria/index.jsp");
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
