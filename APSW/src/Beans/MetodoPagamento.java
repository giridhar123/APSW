package Beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.DatabaseConnection;

public class MetodoPagamento {

	private String numeroCarta;
	private String scadenza;
	private String vcc;
	
	
	//costruttori
	public MetodoPagamento() { }
	public MetodoPagamento(String numeroCarta) {
		this(numeroCarta, null, null);
	}
	public MetodoPagamento(String numeroCarta, String scadenza, String vcc) {
		setNumeroCarta(numeroCarta);
		setScadenza(scadenza);
		setVcc(vcc);
	}
	
	//metodi getter
	public String getNumeroCarta() {
		return numeroCarta;
	}
	public String getScadenza() {
		return scadenza;
	}
	public String getVcc() {
		return vcc;
	}
	
	//metodi setter
	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}
	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}
	public void setVcc(String vcc) {
		this.vcc = vcc;
	}
	
	
	public boolean isContanti() {
		return numeroCarta.equals("Contanti");
	}
	
	//metodo che restituisce tutti i metodi di pagamento di un certo utente
	public static List<MetodoPagamento> getMetodiPagamento(int refUtente) {
		List<MetodoPagamento> metodiPagamento = new ArrayList<>();
    	
    	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			String query = "SELECT NumeroCarta, Scadenza, VCC "
	    			+ "FROM MetodoPagamento "
	    			+ "WHERE RefUtente = ?";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setInt(1, refUtente);
			
			ResultSet rs = stm.executeQuery();
			
	    	while (rs.next()) {
	    		String numeroCarta = rs.getString(1);
	    		String scadenza = rs.getString(2);
	    		String vcc = rs.getString(3);
	    		MetodoPagamento metodo = new MetodoPagamento(numeroCarta, scadenza, vcc);
	    		metodiPagamento.add(metodo);
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return metodiPagamento;
	}
	
	//metodo che elimina un metodo di pagamento
	public static boolean deleteMetodoPagamento(String numeroCarta, Utente utente) {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		String query = "DELETE FROM MetodoPagamento WHERE "
				+ "NumeroCarta = ? "
				+ "AND RefUtente = ?";
		try {
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setString(1, numeroCarta);
			stm.setInt(2, utente.getCodUtente());
			
			return !(stm.execute());
		} catch (SQLException e) {
			return false;
		}
	}
}
