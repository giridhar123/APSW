package Database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
 * Classe Singleton per la connessione al DB
 */

public class DatabaseConnection {

	private static DatabaseConnection reference;
	private static final String JNDINAME = "jdbc/Lavalle";
	private Connection connection = null;
	
	private DatabaseConnection(String jndiname) {
	    try {
	    	DataSource dataSource = (DataSource) new InitialContext().lookup("java:comp/env/" + jndiname);
	        connection = dataSource.getConnection();
	    } catch (NamingException e) {
	    	// JNDI non configurato
	    	throw new IllegalStateException(jndiname + " is missing in JNDI!", e);
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DatabaseConnection getInstance() {
		if (reference == null)
			reference = new DatabaseConnection(JNDINAME);
		
		return reference;
	}
	
	public Connection getConnection() {
		return this.connection;
	}
}