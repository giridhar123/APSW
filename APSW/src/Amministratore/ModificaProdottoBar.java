package Amministratore;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.ProdottoBar;

/**
 * Servlet implementation class aggiungiProdottoBar
 */
@WebServlet("/amministratore/modificaProdottoBar")
public class ModificaProdottoBar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaProdottoBar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer codProdotto = Integer.parseInt(request.getParameter("codProdotto"));
		ProdottoBar prodottoBar = ProdottoBar.getProdotto(codProdotto);
		
		request.setAttribute("prodottoBar", prodottoBar);
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("/amministratore/modificaProdotto.jsp");
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
