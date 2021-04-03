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
 * Servlet implementation class modifyProdottoBar
 */
@WebServlet("/amministratore/modifyProdottoBar")
public class ModifyProdottoBar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyProdottoBar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codProdotto = Integer.parseInt(request.getParameter("codProdotto"));
		String nomeProdotto = request.getParameter("nomeProdotto");
		float costo = Float.parseFloat(request.getParameter("costo"));
		
		boolean success = ProdottoBar.modifyProdottoBar(nomeProdotto, costo, codProdotto);

		if (success)
			response.sendRedirect("gestioneProdotti");
		else {
			String errorText = "Si è verificato un errore durante la modifica del prodotto. Riprovare.";
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
