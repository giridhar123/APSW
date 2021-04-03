package Cassiere;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Ordine;

/**
 * Servlet implementation class ordiniCompletati
 */
@WebServlet("/cassiere/ordiniCompletati")
public class OrdiniCompletati extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdiniCompletati() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sData = request.getParameter("data");
		List<Ordine> oridiniCompletati = null;
		Date data;
		if (sData == null || sData.equals("")) { //L'utente ha appena aperto la pagina quindi mostro gli ordini della data odierna
			data = new Date(new java.util.Date().getTime());
			sData = data.toString();
		}

		oridiniCompletati = Ordine.getOrdiniCompletati(sData);
		
		request.setAttribute("oridiniCompletati", oridiniCompletati);
		request.setAttribute("data", sData);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cassiere/ordiniCompletati.jsp");
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
