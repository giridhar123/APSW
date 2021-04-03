package Beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.DatabaseConnection;

public class Turno {
	
	private int codTurno;
	private String fasciaOraria;
	
	//costruttori
	public Turno() { }
	public Turno(int codTurno, String fasciaOraria) {
		setCodTurno(codTurno);
		setFasciaOraria(fasciaOraria);
	}
	
	//metodi getter
	public int getCodTurno() {
		return codTurno;
	}
	public String getFasciaOraria() {
		return this.fasciaOraria;
	}
	
	//metodi setter
	public void setCodTurno(int codTurno) {
		this.codTurno = codTurno;
	}
	public void setFasciaOraria(String fasciaOraria) {
		this.fasciaOraria = fasciaOraria;
	}
	
	//metodo che restituisce tutti i turni
	public static List<Turno> getTurni() {
		List<Turno> turni = new ArrayList<>();
    	
    	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			String query = "SELECT CodTurno, FasciaOraria "
	    			+ "FROM Turno ";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			
	    	while (rs.next()) {
	    		int codTurno = rs.getInt(1);
	    		String fasciaOraria = rs.getString(2);
	    		Turno turno = new Turno(codTurno, fasciaOraria);
	    		turni.add(turno);
	    	}
		} catch (SQLException e) {
			System.out.println("La tabella Turno è vuota!");
		}
		
		return turni;
	}
	
	//metodo che restituisce il turno associato ad una determinata fascia oraria
	public static Turno getTurno(String fasciaOraria) {
		/* 
		 * Recupero il Codice del turno dal DB relativo alla fascia oraria
		 */
		
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		
		String query = "SELECT CodTurno FROM Turno WHERE FasciaOraria = ?";
		PreparedStatement stm;
		try {
			stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setString(1, fasciaOraria);
			
			ResultSet rs = stm.executeQuery();
			
			if (rs.next())
			{
				int codTurno = rs.getInt(1);
				return (new Turno(codTurno, fasciaOraria));
			}
			else return null;
			
		} catch (SQLException e) {
			return null;
		}		
	}
}
