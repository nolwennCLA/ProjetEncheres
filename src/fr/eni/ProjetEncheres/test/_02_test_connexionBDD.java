package fr.eni.ProjetEncheres.test;

import java.sql.Connection;
import java.sql.SQLException;

import fr.eni.ProjetEncheres.dal.dal.ConnectionProvider;

public class _02_test_connexionBDD {

	public static void main(String[] args) {
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			System.out.println("connexion réussie");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("connexion échouée");
		}

	}

}
