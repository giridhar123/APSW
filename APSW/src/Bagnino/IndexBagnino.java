package Bagnino;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Posto;
import Beans.Prenotazione;
import Beans.Turno;

/**
 * Servlet implementation class Index
 */
@WebServlet("/bagnino/index")
public class IndexBagnino extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexBagnino() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * In base al turno selezionato, vengono mostrate le prenotazioni della data corrente
		 */
		int turnoSelezionato = 1;
		
		if(request.getParameterMap().containsKey("turnoSelezionato"))
			turnoSelezionato = Integer.parseInt(request.getParameter("turnoSelezionato"));
		
		Date oggi = new Date();
		List<Prenotazione> listaPrenotazioni = Prenotazione.getPrenotazioni(oggi, turnoSelezionato);
		List<Prenotazione> prenotazioni = Prenotazione.getPrenotazioni(oggi);
		List<Posto> listaPosti = Posto.getAll();
		List<Turno> turni = Turno.getTurni();
		
		request.setAttribute("dataOggi", oggi);
		request.setAttribute("listaPrenotazioni", listaPrenotazioni);
		request.setAttribute("prenotazioni", prenotazioni);
		request.setAttribute("listaPosti", listaPosti);
		request.setAttribute("turni", turni);
		request.setAttribute("turno", turnoSelezionato);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/bagnino/index.jsp");
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
