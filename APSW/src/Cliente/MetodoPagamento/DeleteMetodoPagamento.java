package Cliente.MetodoPagamento;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.MetodoPagamento;
import Beans.Utente;
import Utils.Utils;

/**
 * Servlet implementation class deleteMetodoPagamento
 */
@WebServlet("/cliente/deleteMetodoPagamento")
public class DeleteMetodoPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMetodoPagamento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String numeroCarta = request.getParameter("numeroCarta");
		
		Utente utente = Utils.getLoggedInUtente(request);
		
		boolean success = MetodoPagamento.deleteMetodoPagamento(numeroCarta, utente);
		response.getWriter().write("{ \"success\": " + success + "}");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
