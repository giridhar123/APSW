package Beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Database.DatabaseConnection;

public class Prenotazione {
	
	private int codPrenotazione;
	private Date data;
	private Turno refTurno;
	private Posto refPosto;
	private MetodoPagamento metodoPagamento;
	private String nome, cognome;
	private boolean pagato;
	private float costo;

	//costruttori
	public Prenotazione () { }
	public Prenotazione(Posto refPosto, Date data, Turno refTurno, MetodoPagamento metodoPagamento, String nome, String cognome, boolean pagato, float costo) {
		this(-1, refPosto, data, refTurno, metodoPagamento, nome, cognome, pagato, costo);
	}
	public Prenotazione(int codPrenotazione, Posto refPosto, Date data, Turno refTurno, MetodoPagamento metodoPagamento, String nome, String cognome, boolean pagato, float costo) {
		setCodPrenotazione(codPrenotazione);
		setRefPosto(refPosto);
		setData(data);
		setRefTurno(refTurno);
		setMetodoPagamento(metodoPagamento);
		setNome(nome);
		setCognome(cognome);
		setPagato(pagato);
		setCosto(costo);
	}
	
	//metodi getter
	public int getCodPrenotazione() {
		return this.codPrenotazione;
	}
	public Posto getRefPosto() {
		return refPosto;
	}
	public Date getData() {
		return data;
	}
	public Turno getRefTurno() {
		return refTurno;
	}
	public MetodoPagamento getMetodoPagamento() {
		return metodoPagamento;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return this.cognome;
	}
	public boolean getPagato() {
		return pagato;
	}
	public float getCosto() {
		return costo;
	}
	
	
	//metodi setter
	public void setRefPosto(Posto refPosto) {
		this.refPosto = refPosto;
	}
	public void setCodPrenotazione(int codPrenotazione) {
		this.codPrenotazione = codPrenotazione;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public void setRefTurno(Turno refTurno) {
		this.refTurno = refTurno;
	}
	public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setPagato(boolean pagato) {
		this.pagato = pagato;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	//metodo che restituisce tutte le prenotazioni di un utente
	public static List<Prenotazione> getPrenotazioni(int refUtente) {
		List<Prenotazione> listaPrenotazioni = new ArrayList<>();
		
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			String query = "SELECT CodPrenotazione, CodPosto, Data, RefTurno, MetodoPagamento, FasciaOraria, Nome, Cognome, numeroSdraio, Pagato, Costo "
					+ "FROM Prenotazione, Turno, Posto "
					+ "WHERE RefUtente = ? "
					+ "AND RefTurno = CodTurno "
					+ "AND RefPosto = CodPosto "
					+ "ORDER BY Data DESC";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setInt(1, refUtente);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				int codPrenotazione = rs.getInt(1);
				int codPosto = rs.getInt(2);
				int numeroSdraio = rs.getInt(9);
				
				Posto posto = new Posto(codPosto, numeroSdraio);
				
				Date data = rs.getDate(3);
				MetodoPagamento metodoPagamento = new MetodoPagamento();
				metodoPagamento.setNumeroCarta(rs.getString(5));
				
				int refTurno = rs.getInt(4);
				String fasciaOraria = rs.getString(6);
				Turno turno = new Turno(refTurno, fasciaOraria);
				
				String nome = rs.getString(7);
				String cognome = rs.getString(8);
				boolean pagato = rs.getBoolean(10);
				float costo = rs.getFloat(11);
				
				Prenotazione prenotazione = new Prenotazione(codPrenotazione, posto, data, turno, metodoPagamento, nome, cognome, pagato, costo);
				listaPrenotazioni.add(prenotazione);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaPrenotazioni;
	}
	
	//metodo che restituisce tutte le prenotazioni in una certa data
	public static List<Prenotazione> getPrenotazioni(Date data) {
		List<Prenotazione> listaPrenotazioni = new ArrayList<>();
		
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			String query = "SELECT CodPrenotazione, CodPosto, RefTurno, MetodoPagamento, FasciaOraria, Nome, Cognome, numeroSdraio, Pagato, Costo "
					+ "FROM Prenotazione, Turno, Posto "
					+ "WHERE Data = ? "
					+ "AND RefTurno = CodTurno "
					+ "AND RefPosto = CodPosto";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setDate(1,  new java.sql.Date(data.getTime()));
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				int codPrenotazione = rs.getInt(1);
				int codPosto = rs.getInt(2);
				int numeroSdraio = rs.getInt(8);
				Posto posto = new Posto(codPosto, numeroSdraio); 
				MetodoPagamento metodoPagamento = new MetodoPagamento();
				metodoPagamento.setNumeroCarta(rs.getString(4));
				
				int refTurno = rs.getInt(3);
				String fasciaOraria = rs.getString(5);
				Turno turno = new Turno(refTurno, fasciaOraria);
				
				String nome = rs.getString(6);
				String cognome = rs.getString(7);
				boolean pagato = rs.getBoolean(9);
				float costo = rs.getFloat(10);
				Prenotazione prenotazione = new Prenotazione(codPrenotazione, posto, new java.sql.Date(data.getTime()), turno, metodoPagamento, nome, cognome, pagato, costo);
				listaPrenotazioni.add(prenotazione);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaPrenotazioni;
	}
	
	//metodo che restituisce tutte le prenotazini in una certa data e in una certa fascia oraria
	public static List<Prenotazione> getPrenotazioni(Date data, int codTurno) {
		List<Prenotazione> listaPrenotazioni = new ArrayList<>();
		
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			String query = "SELECT CodPrenotazione, CodPosto, RefTurno, MetodoPagamento, FasciaOraria, Nome, Cognome, numeroSdraio, Pagato, Costo "
					+ "FROM Prenotazione, Turno, Posto "
					+ "WHERE Data = ? "
					+ "AND RefTurno = CodTurno "
					+ "AND RefPosto = CodPosto "
					+ "AND RefTurno = ?";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setDate(1,  new java.sql.Date(data.getTime()));
			stm.setInt(2, codTurno);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()) {
				int codPrenotazione = rs.getInt(1);
				int codPosto = rs.getInt(2);
				int numeroSdraio = rs.getInt(8);
				Posto posto = new Posto(codPosto, numeroSdraio); 
				MetodoPagamento metodoPagamento = new MetodoPagamento();
				metodoPagamento.setNumeroCarta(rs.getString(4));
				
				int refTurno = rs.getInt(3);
				String fasciaOraria = rs.getString(5);
				Turno turno = new Turno(refTurno, fasciaOraria);
				
				String nome = rs.getString(6);
				String cognome = rs.getString(7);
				boolean pagato = rs.getBoolean(9);
				float costo = rs.getFloat(10);
				Prenotazione prenotazione = new Prenotazione(codPrenotazione, posto, new java.sql.Date(data.getTime()), turno, metodoPagamento, nome, cognome, pagato, costo);
				listaPrenotazioni.add(prenotazione);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaPrenotazioni;
	}
	
	//metodo che permette il pagamento di una prenotazione
	public static boolean pagaPrenotazione(int codPrenotazione) {
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			String query = "UPDATE Prenotazione SET Pagato = 1 WHERE CodPrenotazione = ?";
			PreparedStatement stm = databaseConnection.getConnection().prepareCall(query);
			stm.setInt(1, codPrenotazione);
			
			return !(stm.execute());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean deletePrenotazione(int codPrenotazione) {
		String query = "DELETE FROM Prenotazione WHERE CodPrenotazione = ?";
		DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		PreparedStatement stm;
		try {
			stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setInt(1, codPrenotazione);
			return !(stm.execute());
		} catch (SQLException e) {
			return false;
		}
	}
}
