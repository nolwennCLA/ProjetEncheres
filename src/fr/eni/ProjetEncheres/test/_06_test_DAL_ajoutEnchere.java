package fr.eni.ProjetEncheres.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import fr.eni.ProjetEncheres.bll.article.ArticleManager;
import fr.eni.ProjetEncheres.bll.article.ArticleManagerSing;
import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManager;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManagerSingl;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.bo.Utilisateur;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;
import fr.eni.ProjetEncheres.dal.enchere.DAL_EnchereException;
import fr.eni.ProjetEncheres.dal.enchere.DAOEnchere;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public class _06_test_DAL_ajoutEnchere {

	public static void main(String[] args) throws UtilisateurExceptionBLL, BLL_CategorieException, DAL_CategorieException, BLL_ArticleException, DAL_ArticleException, BLL_RetraitException, DAL_RetraitException, ParseException, DAL_EnchereException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		UtilisateurManager um = UtilisateurManagerSingl.getInstance();
		ArticleManager am = ArticleManagerSing.getInstance();
		
//		EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
		DAOEnchere enchereDAO = DAOFactory.getDAOEnchere();
		
		
		Utilisateur user = um.getUtilisateurParId(6);
        Article art1 = am.selectionnerArticleParId(11);
        
        Enchere ench = new Enchere(sdf.parse("2021-01-15"), 50, user, art1);
        System.out.println(ench.toString());
        
        enchereDAO.insert(ench);
		
		

	}

}
