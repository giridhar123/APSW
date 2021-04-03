package Cliente.MetodoPagamento;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.MetodoPagamento;
import Beans.Utente;
import Utils.Utils;

/**
 * Servlet implementation class newMetodoPagamento
 */
@WebServlet("/cliente/creaMetodoPagamento")
public class CreaMetodoPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaMetodoPagamento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = Utils.getLoggedInUtente(request);
		String numeroCarta = request.getParameter("numeroCarta");	
		String scadenzaAnno = request.getParameter("anno");
		String scadenzaMese = request.getParameter("mese");
		String vcc = request.getParameter("vcc");
		
		String scadenza = scadenzaMese + "/" + scadenzaAnno.charAt(2) + scadenzaAnno.charAt(3);
		MetodoPagamento metodoPagamento = new MetodoPagamento(numeroCarta, scadenza, vcc);
		
		boolean success = true;
		
		/* 
		 * Controllo se l'utente non ha già inserito questo metodo di pagamento
		 */		
		List<MetodoPagamento> metodiPagamento = utente.getMetodiPagamento();
		for (int i = 0; i < metodiPagamento.size(); ++i) {
			if (metodiPagamento.get(i).getNumeroCarta().equals(numeroCarta))
				success = false;
		}
		
		if (success)
			utente.addMetodoPagamento(metodoPagamento);
		
		response.getWriter().append("{ \"success\": " + success + "}");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
