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
import fr.eni.ProjetEncheres.dal.enchere.EnchereDALException;
import fr.eni.ProjetEncheres.dal.enchere.EnchereDAO;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public class TestClaire {

	public static void main(String[] args) throws UtilisateurExceptionBLL, BLL_ArticleException, DAL_ArticleException, BLL_RetraitException, DAL_RetraitException, ParseException, EnchereDALException, BLL_CategorieException, DAL_CategorieException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	       
        UtilisateurManager um = UtilisateurManagerSingl.getInstance();
        ArticleManager am = ArticleManagerSing.getInstance();
       
        EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
       
       
        Utilisateur user = um.getUtilisateurParId(7);
        Article art1 = am.selectionnerArticle(2);
       
        Enchere ench = new Enchere(sdf.parse("2021-01-15"), 50, user, art1);
        System.out.println(ench.toString());
       
        enchereDAO.insert(ench);

	}

}
