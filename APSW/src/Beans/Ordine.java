package Beans;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import Database.DatabaseConnection;

public class Ordine {
	
	private int refUtente;
	private int codOrdine;
	private String metodoPagamento;
	private int servito;
	private Date data;
	private String nomeUtente;
	
	//costruttori
	public Ordine() {}
	public Ordine( int refUtente, int codOrdine, String metodoPagamento, int servito, Date data) {
		setRefUtente(refUtente);
		setCodOrdine(codOrdine);
		setMetodoPagamento(metodoPagamento);
		setServito(servito);
		setData(data);
	}
	public Ordine( int refUtente, int codOrdine, String metodoPagamento, int servito, Date data, String nomeUtente) {
		setRefUtente(refUtente);
		setCodOrdine(codOrdine);
		setMetodoPagamento(metodoPagamento);
		setServito(servito);
		setData(data);
		setNomeUtente(nomeUtente);
	}
	public Ordine( String metodoPagamento) {
		setMetodoPagamento(metodoPagamento);
		setServito(0);
	}
	public Ordine( String metodoPagamento, int servito) {
		setMetodoPagamento(metodoPagamento);
		setServito(servito);
	}
	
	//metodi setter
	public void setRefUtente(int refUtente) {
		this.refUtente=refUtente;
	}
	public void setCodOrdine(int codOrdine) {
		this.codOrdine=codOrdine;
	}
	public void setMetodoPagamento(String metodoPagamento) {
		this.metodoPagamento=metodoPagamento;
	}
	public void setServito(int servito) {
		this.servito=servito;
	}
	public void setData(Date data) {
		this.data=data;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente=nomeUtente;
	}
	
	//metodi getter
	public int getRefUtente() {
		return refUtente;
	}
	public int getCodOrdine() {
		return codOrdine;
	}
	public String getMetodoPagamento() {
		return metodoPagamento;
	}
	public int getServito() {
		return servito;
	}
	public Date getData() {
		return data;
	}
	public String getNomeUtente() {
		return nomeUtente;
	}
	
	//metodo che restituisce tutti gli ordini di un certo utente
	public static List<Ordine> getOrdini(int refUtente) {
    	List<Ordine> ordini = new ArrayList<>();
    	
    	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			String query ="SELECT RefUtente, CodOrdine, MetodoPagamento, Servito, Data "
	    			+ "FROM OrdineBar "
	    			+ "WHERE OrdineBar.RefUtente= ? "
	    			+ "ORDER BY CodOrdine DESC;";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setInt(1, refUtente);
			
			ResultSet rs = stm.executeQuery();
			
	    	while (rs.next()) {
	    		int codOrdine = rs.getInt(2);
	    		String metodoPagamento = rs.getString(3);
	    		int servito = rs.getInt(4);
	    		Date data = rs.getDate(5);
	    		Ordine ordine = new Ordine(refUtente, codOrdine, metodoPagamento, servito, data);
	    		ordini.add(ordine);
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return ordini;
    }
	
	//metodo che restituisce tutti gli ordini in sospeso, quelli che non sono stati ancora serviti
	public static List<Ordine> getOrdiniSospesi() {
    	List<Ordine> ordini = new ArrayList<>();
    	
    	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			String query ="SELECT RefUtente, CodOrdine, MetodoPagamento, Servito, Data, Nome, Cognome "
	    			+ "FROM OrdineBar, Utente "
	    			+ "WHERE OrdineBar.Servito='0'"
	    			+ "AND OrdineBar.RefUtente=Utente.CodUtente";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
	    	while (rs.next()) {
	    		int refUtente = rs.getInt(1);
	    		int codOrdine = rs.getInt(2);
	    		String metodoPagamento = rs.getString(3);
	    		int servito = rs.getInt(4);
	    		Date data = rs.getDate(5);
	    		String nomeUtente = rs.getString(6)+" "+rs.getString(7);
	    		Ordine ordine = new Ordine(refUtente, codOrdine, metodoPagamento, servito, data, nomeUtente);
	    		ordini.add(ordine);
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return ordini;
    }
	
	//metodo che restituisce tutti gli ordini completati, quelli che sono stati serviti
	public static List<Ordine> getOrdiniCompletati(String data) {
    	List<Ordine> ordini = new ArrayList<>();
    	
    	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			String query ="SELECT RefUtente, CodOrdine, MetodoPagamento, Servito, Data, Nome, Cognome "
	    			+ "FROM OrdineBar, Utente "
	    			+ "WHERE OrdineBar.Servito='1'"
	    			+ "AND date(Data)=?"
	    			+ "AND OrdineBar.RefUtente=Utente.CodUtente";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setString(1,  data);
			ResultSet rs = stm.executeQuery();
			
	    	while (rs.next()) {
	    		int refUtente = rs.getInt(1);
	    		int codOrdine = rs.getInt(2);
	    		String metodoPagamento = rs.getString(3);
	    		int servito = rs.getInt(4);
	    		String nomeUtente = rs.getString(6)+" "+rs.getString(7);
	    		Date data1 = Date.valueOf(data);
	    		Ordine ordine = new Ordine(refUtente, codOrdine, metodoPagamento, servito, data1, nomeUtente);
	    		ordini.add(ordine);
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return ordini;
    }
	
	//metodo che restituisce un ordine specifico partendo dal suo codice
	public static Ordine getOrdine(int codOrdine) {
    	Ordine ordine= new Ordine();
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			String query = "SELECT RefUtente, CodOrdine, MetodoPagamento, Servito, Data, Nome, Cognome "
	    			+ "FROM OrdineBar, Utente "
	    			+ "WHERE OrdineBar.CodOrdine= ? "
	    			+ "AND OrdineBar.RefUtente=Utente.CodUtente";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setInt(1, codOrdine);
			
			ResultSet rs = stm.executeQuery();
			
	    	if (rs.next()) {
	    		int refUtente= rs.getInt(1);
	    		int codOrdine1 = rs.getInt(2);
	    		String metodoPagamento = rs.getString(3);
	    		int servito = rs.getInt(4);
	    		Date data = rs.getDate(5);
	    		String nomeUtente = rs.getString(6)+" "+rs.getString(7);
	    		ordine = new Ordine(refUtente, codOrdine1, metodoPagamento, servito, data, nomeUtente);
	    	
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return ordine;
    }
	
	
	//metodo per completare un ordine
	public static String completaOrdine(int codOrdine) {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		String json = "{\"success\": false }";
		
		try {
			String query = "UPDATE OrdineBar "
					+ "SET Servito='1'"
					+ "WHERE CodOrdine = ? ";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			
			stm.setInt(1, codOrdine);
			stm.execute();
			
			json = "{\"success\": true }";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	//metdo per eliminare un ordine
	public static String deleteOrdine(int codOrdine) {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		String json = "{\"success\": false }";
		
		try {
			String query = "DELETE FROM DettagliOrdine "
					+ "WHERE RefOrdine = ? ";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			
			stm.setInt(1, codOrdine);
			stm.execute();
			
			query = "DELETE FROM OrdineBar "
					+ "WHERE CodOrdine = ? ";
			
			stm = databaseConnection.getConnection().prepareStatement(query);
			
			stm.setInt(1, codOrdine);
			stm.execute();
			
			json = "{\"success\": true }";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return json;
	}
}
