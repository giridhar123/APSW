package Cliente.OrdineBar;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.MetodoPagamento;
import Beans.ProdottoBar;
import Beans.Utente;
import Utils.Utils;

/**
 * Servlet implementation class newOrdineBar
 */
@WebServlet("/cliente/newOrdineBar")
public class NewOrdineBarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewOrdineBarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Utente utente = Utils.getLoggedInUtente(request);
		List<MetodoPagamento> metodiPagamento = utente.getMetodiPagamento();
		List<ProdottoBar> prodottiBar=ProdottoBar.getProdottiBar();
		request.setAttribute("utente", utente);
		request.setAttribute("metodiPagamento", metodiPagamento);
		request.setAttribute("prodottiBar", prodottiBar);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/newOrdine.jsp");
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


