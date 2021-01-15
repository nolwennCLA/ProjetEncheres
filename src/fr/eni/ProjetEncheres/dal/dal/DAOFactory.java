package fr.eni.ProjetEncheres.dal.dal;

import fr.eni.ProjetEncheres.dal.article.DAOArticle;
import fr.eni.ProjetEncheres.dal.article.DAOArticleJdbcImpl;
import fr.eni.ProjetEncheres.dal.enchere.EnchereDAO;
import fr.eni.ProjetEncheres.dal.enchere.EnchereDAOImpl;
import fr.eni.ProjetEncheres.dal.utilisateur.UtilisateurDAO;
import fr.eni.ProjetEncheres.dal.utilisateur.UtilisateurDAOImpl;

public class DAOFactory {

	private static UtilisateurDAO utilisateurDao;
	private static DAOArticle articleDao;
	private static EnchereDAO enchereDAO;

	public static UtilisateurDAO getUtilisateurDAO() {
		if (utilisateurDao == null) {
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
	
	public static EnchereDAO getEnchereDAO() {
		if (enchereDAO == null) {
			enchereDAO = new EnchereDAOImpl();
		}
		return enchereDAO;
	}

}
