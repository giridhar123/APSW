package Cliente.MetodoPagamento;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.MetodoPagamento;
import Beans.Utente;
import Utils.Utils;

/**
 * Servlet implementation class metodiPagamento
 */
@WebServlet("/cliente/metodiPagamento")
public class MetodiPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MetodiPagamento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = Utils.getLoggedInUtente(request);
		List<MetodoPagamento> metodiPagamento = utente.getMetodiPagamento();
		
		request.setAttribute("metodiPagamento", metodiPagamento);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente/metodiPagamento.jsp");
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
