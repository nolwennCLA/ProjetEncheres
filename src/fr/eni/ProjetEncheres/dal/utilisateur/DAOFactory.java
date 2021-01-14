package fr.eni.ProjetEncheres.dal.utilisateur;

import fr.eni.ProjetEncheres.dal.article.DAOArticle;
import fr.eni.ProjetEncheres.dal.article.DAOArticleJdbcImpl;

public class DAOFactory {
	
	private static UtilisateurDAO utilisateurDao;
	private static DAOArticle articleDao;
	
	
	public static UtilisateurDAO getUtilisateurDAO() {
		if(utilisateurDao == null) {
			utilisateurDao = new UtilisateurDAOImpl();
		}
		return utilisateurDao; 
	}
	

	public static DAOArticle getDAOArticle() {
		if (articleDao == null) {
			articleDao = new DAOArticleJdbcImpl();
		}
		return articleDao;
	}
}
