package fr.eni.ProjetEncheres.dal.utilisateur;

import fr.eni.ProjetEncheres.dal.article.DAOArticle;
import fr.eni.ProjetEncheres.dal.article.DAOArticleJdbcImpl;

public class DAOFactory {

	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
	}
	
private static DAOArticle instance;
	
	public static DAOArticle getInstance() {
		if (instance == null) {
			instance = new DAOArticleJdbcImpl();
		}
		return instance;
	}
}
