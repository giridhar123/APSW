package Amministratore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Posto;
import Utils.Utils;

/**
 * Servlet implementation class CreaPosto
 */
@WebServlet("/amministratore/creaPosto")
public class CreaPosto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaPosto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numeroSdraio = Integer.parseInt(request.getParameter("numeroSdraio"));
		float costoTotale = Float.parseFloat(request.getParameter("costoTotale"));
		
		Posto posto = new Posto(-1, numeroSdraio, costoTotale);
		boolean success = Posto.addInDb(posto);
		
		if (success)
			response.sendRedirect("./posti");
		else {
			String errorText = "Si è verificato un errore durante la creazione di un posto. Riprovare";
			String previousPage = "newPosto";
			
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
