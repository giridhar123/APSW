package Beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;

import Utils.Carrello;
import Database.DatabaseConnection;

//classe che modella i dettagli di un ordine

public class DettagliOrdine {
	
	private int refOrdine;
	private String nomeProdotto;
	private int quantita;
	private float costo;
	
	public DettagliOrdine(int refOrdine, String nomeProdotto, int quantita, float costo) {
		this.refOrdine=refOrdine;
		this.nomeProdotto=nomeProdotto;
		this.quantita=quantita;
		this.costo=costo;
	}
	
	//metodi getter
	public int getRefOrdine() {
		return refOrdine;
	}
	public String getProdotto() {
		return nomeProdotto;
	}
	public int getQuantita() {
		return quantita;
	}
	public float getCosto() {
		return costo;
	}
	
	//metodo che aggiunge nel DB un dettaglio di un ordine
	public static void addDettagli(Carrello carrello, int codiceOrdine, int codiceUtente) {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    	try {
			PreparedStatement stm;
			
			for (Entry<Integer, Integer> entry : carrello.getListaProdotti().entrySet()) {
				String query = "SELECT Costo, NomeProdotto "
		    			+ "FROM ProdottoBar "
		    			+ "WHERE ProdottoBar.CodProdotto= ?";
	    		stm = databaseConnection.getConnection().prepareStatement(query);
	    		stm.setInt(1, entry.getKey());
	    		
				ResultSet rs = stm.executeQuery();
					
			    	if (rs.next() ) {
			    		float costo = rs.getFloat(1);
			    		String nomeProdotto = rs.getString(2);
			    		costo= costo * entry.getValue();
			    		stm.execute("INSERT INTO DettagliOrdine (RefOrdine, NomeProdotto, Quantita, Costo) VALUES "
			    						+ "('" + codiceOrdine + "', "
										+ "'" + nomeProdotto + "', "
										+ "'" + entry.getValue() + "', "
										+ "'" + costo + "')");			    		
			    	}  
			}
			
    	} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//metodo che restituisce tutti i dettagli di un ordine
	public static List<DettagliOrdine> getDettagli(int refOrdine) {
		List<DettagliOrdine> dettagliOrdine = new ArrayList<>();
		
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			String query = "SELECT RefOrdine, NomeProdotto, Quantita, DettagliOrdine.Costo "
					+"FROM DettagliOrdine "
					+"WHERE DettagliOrdine.refOrdine=?";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setInt(1, refOrdine);
			ResultSet rs = stm.executeQuery();
			
	    	while (rs.next()) {
	    		int ordine = rs.getInt(1);
	    		String prodotto = rs.getString(2);
	    		int quantita = rs.getInt(3);
	    		float costo = rs.getFloat(4);
	    		
	    		DettagliOrdine dettaglio = new DettagliOrdine(ordine, prodotto, quantita, costo);
	    		dettagliOrdine.add(dettaglio);
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dettagliOrdine;
	}	
}