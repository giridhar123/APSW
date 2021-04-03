package Amministratore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.ProdottoBar;
import Utils.Utils;

/**
 * Servlet implementation class creaProdottoBar
 */
@WebServlet("/amministratore/creaProdottoBar")
public class CreaProdottoBar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaProdottoBar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeProdotto = request.getParameter("nomeProdotto");
		float costo = Float.parseFloat(request.getParameter("costo"));
		
		boolean success = ProdottoBar.creaProdottoBar(nomeProdotto, costo);
		
		if (success)
			response.sendRedirect("gestioneProdotti");
		else {
			String errorText = "Si è verificato un errore durante l'aggiunta del prodotto. Riprovare.";
			String previousPage = "gestioneProdotti";
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
