package Cliente.Prenotazione;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Utente;
import Database.DatabaseConnection;
import Utils.Utils;

/**
 * Servlet implementation class DeletePrenotazione
 */
@WebServlet("/cliente/deletePrenotazione")
public class DeletePrenotazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePrenotazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = Utils.getLoggedInUtente(request);
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		String json;
		
		try {
			String query = "DELETE FROM Prenotazione "
					+ "WHERE CodPrenotazione = ? "
					+ "AND RefUtente = ? "
					+ "AND Data = ?";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			
			int codPrenotazione = Integer.parseInt(request.getParameter("codPrenotazione"));
			String data = request.getParameter("data");
			
			stm.setInt(1, codPrenotazione);
			stm.setInt(2, utente.getCodUtente());
			stm.setString(3, data);
			
			stm.execute();
			
			json = "{\"success\": true }";
		} catch (SQLException e) {
			json = "{\"success\": false }";
		}
			
		response.getWriter().append(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
