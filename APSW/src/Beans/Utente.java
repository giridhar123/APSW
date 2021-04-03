package Beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Database.DatabaseConnection;
import Utils.Utils;

public class Utente {
	
	private String nome, cognome, email, password;
	private int codUtente, idPermesso;

    //costruttori
	public Utente() { }
    public Utente(String nome, String cognome, String email) {
    	setNome(nome);
    	setCognome(cognome);
    	setEmail(email);
    }

    //metodi getter
    public String getNome() {
        return this.nome;
    }
    public String getCognome() {
    	return this.cognome;
    }
    public String getEmail() {
    	return this.email;
    }
    public int getIdPermesso() {
    	return this.idPermesso;
    }
    public int getCodUtente() {
    	return codUtente;
    }
    public String getPassword() {
    	return password;
    }
    
    
    //metodi setter
    public void setNome(String nome) {
        this.nome = nome;
    }public void setCognome(String cognome) {
    	this.cognome = cognome;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    public void setIdPermesso(int idPermesso) {
    	this.idPermesso = idPermesso;
    }
    public void setCodUtente(int codUtente) {
    	this.codUtente = codUtente;
    }
    public void setPassword(String password) {
    	this.password = password;
    }
    
    //Methods
    
    //metodo per registrare un utente nel DB
    public boolean registraNelDB(String password) {
    	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    	Connection connection = databaseConnection.getConnection();
    	try {    		
    		String query = "INSERT INTO Utente "
    				+ "(Nome, Cognome, EMail, IdPermesso, Password) "
    				+ "VALUES (?, ?, ?, ?, ?)";
			PreparedStatement prepStm = connection.prepareStatement(query);
			prepStm.setString(1, nome);
			prepStm.setString(2, cognome);
			prepStm.setString(3, email);
			prepStm.setInt(4, 0); //Id permesso base
			prepStm.setString(5, password);
			
			return !prepStm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }
    
    //metodo per prelevare un utente dal DB
    public static Utente getUtenteFromDB(String email) {
    	Utente utente = null;
    	
    	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			String query = "SELECT "
					+ "CodUtente, Nome, Cognome, IdPermesso, Password "
					+ "FROM Utente WHERE EMail = ?";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setString(1, email);
			
			ResultSet rs = stm.executeQuery();
			
			if (rs.next()) {
				int codUtente = rs.getInt(1);
				String nome = rs.getString(2);
				String cognome = rs.getString(3);
				int idPermesso = rs.getInt(4);
				String password = rs.getString(5);
				
				utente = new Utente(nome, cognome, email);
				utente.setIdPermesso(idPermesso);
				utente.setCodUtente(codUtente);
				utente.setPassword(password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}    	
    	
    	return utente;
    }
    
    //metodo per ottenere tutti i metodi di pagamento di un utente
    public List<MetodoPagamento> getMetodiPagamento() {
    	return MetodoPagamento.getMetodiPagamento(codUtente);
    }
    
    //metodo per ottenere tutte le prenotazioni di un utente
    public List<Prenotazione> getPrenotazioni() {
    	return Prenotazione.getPrenotazioni(codUtente);
    }
    
    //metodo per ottenere tutti gli ordini di un utente
    public List<Ordine> getOrdini() {
    	return Ordine.getOrdini(codUtente);
    }
    
    //metodo per aggiungere un metodo di pagamento all'utente
    public void addMetodoPagamento(MetodoPagamento metodoPagamento) {
    	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    	try {
    		String query = "INSERT INTO MetodoPagamento VALUES (?, ?, ?, ?)";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setInt(1, codUtente);
			stm.setString(2, metodoPagamento.getNumeroCarta());
			stm.setString(3, metodoPagamento.getScadenza());
			stm.setString(4, metodoPagamento.getVcc());
			
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    //metodo per aggiungere una prenotazione dell'utente
    public boolean addPrenotazione(Prenotazione prenotazione) {
    	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    	try {
    		String query = "INSERT INTO Prenotazione (RefUtente, Data, RefPosto, RefTurno, MetodoPagamento, Nome, Cognome, Pagato, Costo) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setInt(1, codUtente);
			stm.setDate(2, new java.sql.Date(prenotazione.getData().getTime()));
			stm.setInt(3, prenotazione.getRefPosto().getCodPosto());
			stm.setInt(4, prenotazione.getRefTurno().getCodTurno());
			stm.setString(5, prenotazione.getMetodoPagamento().getNumeroCarta());
			stm.setString(6, prenotazione.getNome());
			stm.setString(7, prenotazione.getCognome());
			stm.setBoolean(8, prenotazione.getPagato());
			stm.setFloat(9, prenotazione.getCosto());
			
			return !(stm.execute());
		} catch (SQLException e) {
			return false;
		}
    }
    
  //metodo per aggiungere un nuovo ordine dell'utente
    public int addOrdine(Ordine ordine) {
    	int codice=-1;
    	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    	try {
    		String query = "INSERT INTO OrdineBar (RefUtente, MetodoPagamento, Servito) VALUES "
					+ "(?, ?, ?)";
    		
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setInt(1, codUtente);
			stm.setString(2, ordine.getMetodoPagamento());
			stm.setInt(3, ordine.getServito());
			
			stm.execute();
			
			//mi serve sapere il codice dell'ordine che ho appena creato (è autoincrementante nel db)
			//per poi creare i dettagli dell'ordine
			
			ResultSet rs = stm.executeQuery("SELECT CodOrdine "
											+ "FROM OrdineBar "
											+ "WHERE CodOrdine = (SELECT max(CodOrdine) "
																+ "FROM OrdineBar)");
			while (rs.next()) {
				codice = rs.getInt(1);
			}
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	return codice;
    }
    
    //metodo che modifica i permessi di un utente
    public boolean cambiaPermesso(int nuovoPermesso) {
    	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		try {
			String query = "UPDATE Utente SET idPermesso = ? WHERE CodUtente = ?";
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setInt(1, nuovoPermesso);
			stm.setInt(2, codUtente);
			
			stm.execute();
		} catch (SQLException e) {
			return false;
		}
    	   	
    	return true;
    }
    
    //metodo che aggiorna i dati di un utente
    public static boolean updateUtente(String currentEmail, String newEmail, String nome, String cognome, String currentPassword, String newPassword) {
    	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    		
    	try {
    		String query = "UPDATE Utente SET "
					+ "email = ?, "
					+ "nome = ?, "
					+ "cognome = ?, "
					+ "password = ? "
					+ "WHERE email = ?";
    		
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setString(1, newEmail);
			stm.setString(2, nome);
			stm.setString(3, cognome);
			
			String password;
			// controllo se la password è nulla o se è uguale all'hash di uno spazio vuoto, in questo caso l'utente non ha modificato la password
			
			if (newPassword == null || newPassword.equals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"))
				password = currentPassword;
			else password = newPassword;
			
			stm.setString(4, password);
			stm.setString(5, currentEmail);
			
			return (!stm.execute());
		} catch (SQLException e) {
			return false;
		}
    }
    
    //metodo per modificare la password dell'utente
    public static boolean changePassword(String email, String newPassword) {
    	System.out.println("New password è:" + newPassword);
    	DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
		
    	try {
    		String query = "UPDATE Utente SET "
					+ "password = ? "
					+ "WHERE email = ?";
    		
			PreparedStatement stm = databaseConnection.getConnection().prepareStatement(query);
			stm.setString(1, Utils.getSha256(newPassword));
			stm.setString(2, email);
			
			return (!stm.execute());
		} catch (SQLException e) {
			return false;
		}
    }
}
