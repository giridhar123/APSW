package Cliente.OrdineBar;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Utils.Carrello; 

/**
 * Servlet implementation class VisualizzaCarrello
 */
@WebServlet("/cliente/aggiornaCarrello")
public class AggiornaCarrelloCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiornaCarrelloCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		
		String cmd = request.getParameter("cmd");
		int codProdotto = Integer.parseInt(request.getParameter("codProdotto"));
		
		if (carrello == null) {
			carrello = new Carrello();
		}
		
		if (cmd.equals("aggiungi")) {
			int quantita = Integer.parseInt(request.getParameter("qtn"));
			carrello.aggiungiQuantita(codProdotto, quantita);
		}			
		else if (cmd.equals("rimuovi"))
			carrello.rimuoviProdotto(codProdotto);
		
		session.setAttribute("carrello", carrello);
		response.getWriter().append("{\"success\": true}");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
