package fr.eni.ProjetEncheres.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import fr.eni.ProjetEncheres.bll.article.ArticleManager;
import fr.eni.ProjetEncheres.bll.article.ArticleManagerSing;
import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManager;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManagerSing;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.retrait.RetraitManager;
import fr.eni.ProjetEncheres.bll.retrait.RetraitManagerSing;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManager;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManagerSingl;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.bo.Retrait;
import fr.eni.ProjetEncheres.bo.Utilisateur;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;
import fr.eni.ProjetEncheres.dal.retrait.DAORetrait;

public class _04_test_DAL_gestionRetrait {
	
	
	public static void main(String[] args) throws UtilisateurExceptionBLL, BLL_CategorieException, DAL_CategorieException, ParseException, DAL_RetraitException, BLL_RetraitException, DAL_ArticleException, BLL_ArticleException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RetraitManager rm = RetraitManagerSing.getInstance();
		UtilisateurManager um = UtilisateurManagerSingl.getInstance();
		CategorieManager cm = CategorieManagerSing.getInstance();
		ArticleManager am = ArticleManagerSing.getInstance();
		
		DAORetrait retraitDao = DAOFactory.getDAORetrait();
		
		//récupération d'un utilisateur
		Utilisateur user = um.getUtilisateurParId(1);
//		System.out.println(user.toString());
		
		//récupération d'une catégorie
		Categorie cat = cm.selectionnerCategorie(1);
//		System.out.println(cat.toString());
		
		//création de 2 retraits
		Retrait ret1 = new Retrait("av.Goncourt", "35000", "Rennes");
//		System.out.println(ret.toString());
		
		Retrait ret2 = new Retrait("rue vermeil", "29400", "Brest");
		System.out.println(ret2.toString());
		
		//création d'un articles
		Article art = new Article("peigne", "peigne traditionnel", sdf.parse("2021-01-20"), sdf.parse("2021-01-25"), 150, 0, "AV", cat, user, null);
		System.out.println(art.toString());
		
		System.out.println("----------------------");
		
//		rm.creerRetrait(art, ret2);
		am.creerArticle(art, ret1);
		System.out.println(ret1.toString());
		System.out.println(art.toString());

	}

}
