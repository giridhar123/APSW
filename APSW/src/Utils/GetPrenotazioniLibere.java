package Utils;

import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Posto;

/**
 * Servlet implementation class PrenotazioniLibere
 */
@WebServlet("/GetPrenotazioniLibere")
public class GetPrenotazioniLibere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPrenotazioniLibere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sData = (String) request.getParameter("data");
		String numeroPersone = (String) request.getParameter("numeroPersone");
		
		//Converto la data ricevuta in oggetto Date
		Date data = Date.valueOf(sData);
			
		Map<String, List<Posto>> postiLiberi = Posto.getPostiLiberi(numeroPersone, data);
			
		/*
		 * Creo la stringa JSON
		 */
		
		Set<String> keys = postiLiberi.keySet();
		Iterator<String> iterator = keys.iterator();
		StringBuilder json = new StringBuilder();
		
		json.append("{");
		while (iterator.hasNext()) {
			String key = iterator.next();
			json.append("\"" + key + "\": [");
			
			List<Posto> list = postiLiberi.get(key);
			for (int i = 0; i < list.size() - 1; ++i) {
				json.append("[\"" + list.get(i).getCodPosto() + "\", \"" + list.get(i).getCostoTotale() + "\"], ");
			}
			
			int lastIndex = list.size() - 1;
			json.append("[\"" + list.get(lastIndex).getCodPosto() + "\", \"" + list.get(lastIndex).getCostoTotale() + "\"]]");
			
			if (iterator.hasNext())
				json.append(",");
		}
		json.append("}");
		
		response.getWriter().append(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
