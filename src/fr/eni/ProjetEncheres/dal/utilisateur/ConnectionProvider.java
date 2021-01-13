package fr.eni.ProjetEncheres.dal.utilisateur;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

abstract class ConnectionProvider {
	private static DataSource dataSource;
	private static final String URL="jdbc:sqlserver://localhost:1433;databaseName=ENCHERE";
	private static final String USER="sa";
	private static final String PASSWORD="Pa$$w0rd";
	private static final String DRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	/**
	 * Au chargement de la classe, la DataSource est recherchée dans l'arbre JNDI
	 */
	static
	{
		Context context;
		try {
			context = new InitialContext();
			ConnectionProvider.dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
//			e.printStackTrace();
//			throw new RuntimeException("Impossible d'accéder à la base de données");
		}
	}
	
	/**
	 * Cette méthode retourne une connection opérationnelle issue du pool de connexion
	 * vers la base de données. 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException
	{
		if(ConnectionProvider.dataSource!=null) {
			return ConnectionProvider.dataSource.getConnection();
		}
		else {
			Connection cnx = null;
			try{
				Class.forName(DRIVER);
				cnx = DriverManager.getConnection(URL, USER, PASSWORD);
			}
			catch(SQLException e){
				throw new SQLException("Impossible d'obtenir une connexion:"+e.getMessage());
			} catch (ClassNotFoundException e) {
				throw new SQLException("Problème de Driver:"+e.getMessage());
			}
			return cnx;			
		}
	}
}