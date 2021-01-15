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
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public class _01_test_BLL {
	
	

	public static void main(String[] args) throws ParseException, BLL_CategorieException, DAL_CategorieException, UtilisateurExceptionBLL {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		ArticleManager am = ArticleManagerSing.getInstance();
//		RetraitManager rm = RetraitManagerSing.getInstance();
		CategorieManager cm = CategorieManagerSing.getInstance();
		UtilisateurManager um = UtilisateurManagerSingl.getInstance();
		
//		Utilisateur user = new Utilisateur("pseudo", "nom", "prenom", "@gmail", "06700", "rue des Palmiers", "29000", "Quimper", "1234");
//		user.setNoUtilisateur(1);
		
		//insertion et récupération d'un utilisateur
		//um.addUtilisateur(new Utilisateur("pseudo", "nom", "prenom", "@gmail", "06700", "rue des Palmiers", "29000", "Quimper", "1234"));
		Utilisateur user = um.getUtilisateurParId(1);
		System.out.println(user.toString());
		
		//récupération de 3 catégories
		Categorie cat1 = cm.selectionnerCategorie(1);
		Categorie cat2 = cm.selectionnerCategorie(2);
		Categorie cat3 = cm.selectionnerCategorie(3);
		System.out.println("3 catégories récupérées");
		
		//création de 3 retraits
		Retrait ret1 = new Retrait("rue des Palmiers", "29000", "Quimper");
		Retrait ret2 = new Retrait("av.Goncourt", "35000", "Rennes");
		Retrait ret3 = new Retrait("av.Gambetta", "75020", "Paris");
		System.out.println("3 retraits créés");
		
		//création de 3 articles
		Article art1 = new Article("peigne", "peigne traditionnel", sdf.parse("2021-01-15"), sdf.parse("2021-01-15"), 150, 0, "AV", cat1, user, null);
		Article art2 = new Article("brosse", "brosse à cheveux", sdf.parse("2021-01-15"), sdf.parse("2021-01-15"), 150, 0, "AV", cat2, user, null);
		Article art3 = new Article("valise", "cuir véritable", sdf.parse("2021-01-15"), sdf.parse("2021-01-15"), 150, 0, "AV", cat3, user, null);
		System.out.println("3 articles créés");

		
		//insertion des 3 articles dans la bdd
		try {
			am.creerArticle(art1, ret1);
			am.creerArticle(art2, ret2);
			am.creerArticle(art3, ret3);
		} catch (BLL_ArticleException | BLL_RetraitException | DAL_ArticleException | DAL_RetraitException e) {
			System.out.println(e.getMessage());
		}
//		
//		System.out.println("LISTER ARTICLES");
//		try {
//			for(Article a : am.listerArticles()) {
//				System.out.println(a.toString());
//			}
//		} catch (BLL_ArticleException | DAL_ArticleException | UtilisateurExceptionBLL | BLL_RetraitException | DAL_RetraitException e) {
//			System.out.println(e.getMessage());
//		}
//		
//		
//		System.out.println("---------------");
//		System.out.println("LISTER RETRAITS");
//		try {
//			for(Retrait r : rm.listerRetraits()) {
//				System.out.println(r.toString());
//			}
//		} catch (BLL_RetraitException | DAL_RetraitException e) {
//			System.out.println(e.getMessage());
//		}
//		
		
	}

}
