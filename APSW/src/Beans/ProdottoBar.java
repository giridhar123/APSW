package Beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.DatabaseConnection;

public class ProdottoBar {
	
	private int codProdotto;
	private String nomeProdotto;
	private float costo;
	
	public ProdottoBar() {}
	
	public ProdottoBar (int codProdotto, String nomeProdotto, float costo) {
		setCodProdotto(codProdotto);
		setNomeProdotto(nomeProdotto);
		setCosto(costo);
	}
	
	//metodi setter
	public void setCodProdotto(int codProdotto) {
		this.codProdotto=codProdotto;
	}
	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto=nomeProdotto;
	}
	public void setCosto(float costo) {
		this.costo=costo;
	}

	//metodi getter
	public int getCodProdotto() {
		return codProdotto;
	}
	public String getNomeProdotto() {
		return nomeProdotto;
	}
	public float getCosto() {
		return costo;
	}
	
	//metodo che restituisce tutti i prodotti disponibili al bar
	public static List<ProdottoBar> getProdottiBar() {
    	List<ProdottoBar> prodottiBar = new ArrayList<>();
    	
    	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			String query = "SELECT CodProdotto, NomeProdotto, Costo "
	    				+ "FROM ProdottoBar ";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			
			ResultSet rs = stm.executeQuery();
	    	while (rs.next()) {
	    		int codProdotto = rs.getInt(1);
	    		String nomeProdotto = rs.getString(2);
	    		float costo = rs.getFloat(3);
	    		ProdottoBar prodottoBar= new ProdottoBar(codProdotto, nomeProdotto, costo);
	    		prodottiBar.add(prodottoBar);
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return prodottiBar;
    }
	
	//metodo che restituisce un prodotto specifico partendo dal suo codice
	public static ProdottoBar getProdotto(int codProdotto) {
    	ProdottoBar prodottoBar= new ProdottoBar();
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			String query = "SELECT CodProdotto, NomeProdotto, Costo "
	    			+ "FROM ProdottoBar "
	    			+ "WHERE CodProdotto= ? ";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setInt(1, codProdotto);
			
			ResultSet rs = stm.executeQuery();
			
	    	if (rs.next()) {
	    		String nomeProdotto = rs.getString(2);
	    		float costo = rs.getFloat(3);
	    		prodottoBar = new ProdottoBar(codProdotto, nomeProdotto, costo);
	    	
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return prodottoBar;
    }
	
	//metodo che crea un nuovo prodotto bar
	public static boolean creaProdottoBar(String nomeProdotto, float costo) {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		boolean success = false;
		try {
			String query = "INSERT INTO ProdottoBar (NomeProdotto, Costo) "
						+ "VALUES (?, ?);";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			
			stm.setString(1, nomeProdotto);
			stm.setFloat(2, costo);
			
			if (!(stm.execute())){
				success=true;
			}
		} catch (SQLException e) {
			success = false;
		}
		return success;
	}
	
	//metodo per eliminare un prodotto bar
	public static String deleteProdottoBar(int codProdotto) {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		String json = "{\"success\": false }";
		
		try {
			
			//controllo se ci sono, per ogni ordine, dei prodotti che sono stati già cancellati
			String query = "DELETE FROM ProdottoBar "
					+ "WHERE codProdotto = ? ";
			
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			
			stm.setInt(1, codProdotto);
			stm.execute();
			
			json = "{\"success\": true }";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	//metodo per modificare un prodotto bar
	public static boolean modifyProdottoBar(String nomeProdotto, float costo, int codProdotto) {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		boolean success = false;
		try {
			String query = "UPDATE ProdottoBar "
						+ "SET NomeProdotto = ?, Costo = ? "
						+ " WHERE (CodProdotto = ?);";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			
			stm.setString(1, nomeProdotto);
			stm.setFloat(2, costo);
			stm.setInt(3, codProdotto);
			
			if (!(stm.execute())){
				success=true;
			}
		} catch (SQLException e) {
			success = false;
		}
		return success;
	}
}
