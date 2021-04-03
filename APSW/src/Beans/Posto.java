package Beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Database.DatabaseConnection;

public class Posto {
	
	private int codPosto;
	private int numeroSdraio;
	private float costoTotale;
	
	//costruttori
	public Posto() { }
	public Posto(int codPosto, int numeroSdraio) {
		setCodPosto(codPosto);
		setNumeroSdraio(numeroSdraio);
	}
	public Posto(int codPosto, int numeroSdraio, float costoTotale) {
		setCodPosto(codPosto);
		setNumeroSdraio(numeroSdraio);
		setCostoTotale(costoTotale);
	}
	public Posto(int codPosto) {
		setCodPosto(codPosto);
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			String query = "SELECT NumeroSdraio, CostoTotale FROM Posto WHERE CodPosto = ?";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setInt(1, codPosto);
			
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				setNumeroSdraio(rs.getInt(1));
				setCostoTotale(rs.getFloat(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//metodi setter
	public void setCodPosto(int codPosto) {
		this.codPosto = codPosto;
	}
	public void setNumeroSdraio(int numeroSdraio) {
		this.numeroSdraio = numeroSdraio;
	}
	public void setCostoTotale(float costoTotale) {
		this.costoTotale = costoTotale;
	}
	
	//metodi getter
	public int getNumeroSdraio() {
		return numeroSdraio;
	}
	public int getCodPosto() {
		return codPosto;
	}
	public float getCostoTotale() {
		return costoTotale;
	}
	
	
	//metodo che restiruisce tutti i posti
	public static List<Posto> getAll() {
		List<Posto> list = new ArrayList<>();
		
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		String query = "SELECT CodPosto, NumeroSdraio, CostoTotale FROM Posto";
		
		try {
			Statement stm = databaseConnection.getConnection().createStatement();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				int codPosto = rs.getInt(1);
				int numeroSdraio = rs.getInt(2);
				float costoTotale = rs.getFloat(3);
				Posto posto = new Posto(codPosto, numeroSdraio, costoTotale);
				list.add(posto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//metodo che aggiunge un nuovo posto nel DB
	public static boolean addInDb(Posto posto) {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		String query = "INSERT INTO Posto (NumeroSdraio, CostoTotale) "
					+ "VALUES "
					+ "(?, ?)";
		
		try {
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setInt(1, posto.getNumeroSdraio());
			stm.setFloat(2, posto.getCostoTotale());
			
			return !(stm.execute());
		} catch (SQLException e) {
			return false;
		}
	}
	
	/*
	 * Restituisce una mappa che ha per chiave le fasce orarie e per valore una lista
	 * di posti liberi per quella fascia oraria
	 */
	
	public static Map<String, List<Posto>> getPostiLiberi(String numeroPersone, Date data) {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		Map<String, List<Posto>> myMap = new HashMap<>();
		try {
			/*
			 * Cerco i posti e le relative fasce orarie per i quali è possibile prenotare
			 */
			Statement stm = databaseConnection.getConnection().createStatement();
			ResultSet rs = stm.executeQuery("SELECT CodPosto, FasciaOraria, CostoTotale, NumeroSdraio " +
			"FROM Turno as turno1, Posto "+
			"WHERE NumeroSdraio = " + numeroPersone + " " +
			"AND CodPosto NOT IN "+
				"( " +
					"SELECT CodPosto " +
					"FROM Prenotazione, Posto, Turno as turno2 " +
					"WHERE Data = \"" + data.toString() + "\"" +
					"AND RefPosto = CodPosto " +
					"AND RefTurno = CodTurno " +
					"AND NumeroSdraio = " + numeroPersone + " " +
					"AND turno1.codTurno = turno2.codTurno " +
				") " +
			"ORDER BY FasciaOraria DESC");
			
			String fasciaOraria;
			int codicePosto, numeroSdraio;
			float costoTotale;
			List<Posto> tempList;
			
			/* 
			 * Metto i risultati in una mappa per poi iterare su di essa per generare la stringa JSON
			 */
			while (rs.next()) {
				codicePosto = rs.getInt(1);
				fasciaOraria = rs.getString(2);
				costoTotale = rs.getFloat(3);
				numeroSdraio = rs.getInt(4);
				
				Posto posto = new Posto(codicePosto, numeroSdraio, costoTotale);
				
				if (myMap.containsKey(fasciaOraria)) {
					tempList = myMap.get(fasciaOraria);
					tempList.add(posto);
					myMap.replace(fasciaOraria, tempList);
				}
				else {
					tempList = new ArrayList<>();
					tempList.add(posto);
					myMap.put(fasciaOraria, tempList);
				}
			}
		} catch (SQLException e) {
			return new HashMap<String, List<Posto>>();
		}
		
		return myMap;
	}
}
