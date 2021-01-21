package fr.eni.ProjetEncheres.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.eni.ProjetEncheres.bll.article.ArticleManager;
import fr.eni.ProjetEncheres.bll.article.ArticleManagerSing;
import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManager;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManagerSing;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManager;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManagerSingl;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public class _05_test_BLL_DAL_manager {

	public static void main(String[] args) throws UtilisateurExceptionBLL, BLL_CategorieException, DAL_CategorieException, ParseException, BLL_ArticleException, BLL_RetraitException, DAL_ArticleException, DAL_RetraitException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		UtilisateurManager um = UtilisateurManagerSingl.getInstance();
		CategorieManager cm = CategorieManagerSing.getInstance();
		ArticleManager am = ArticleManagerSing.getInstance();
		

		Article a = am.selectionnerArticleParId(97);
		System.out.println(a.getDateDebutEncheres());
		
		a.setDateDebutEncheres(new Date());
		
		a = am.modifierArticle(a);
		System.out.println(a.getDateDebutEncheres());
		
		
		
		
//		Utilisateur user1 = new Utilisateur("truc", "tarin", "titi", "@yahoo", "06700", "rue Jolie", "29000", "Quimper", "123");
//		Utilisateur user2 = new Utilisateur("bidule", "bizarre", "boutchou", "@gmail", "07800", "impasse Vérité", "35000", "Rennes", "456");
//		Utilisateur user3 = new Utilisateur("lili", "lulu", "lala", "@aol", "08900", "boulevard de la mort", "44000", "Nantes", "789");
//		
//		System.out.println(user1.toString());
//		System.out.println(user2.toString());
//		System.out.println(user3.toString());
//		
//		um.addUtilisateur(user1);
//		um.addUtilisateur(user2);
//		um.addUtilisateur(user3);
//		System.out.println("insert utilisateurs ok");
//		
//		List<Utilisateur> lstUt = um.getListUtilisateur();
//		System.out.println("liste utilisateurs ok");
//		
//		for(Utilisateur u : lstUt) {
//			System.out.println(u.toString());
//		}

		
		
		
		
		
		
//				Utilisateur user1 = um.getUtilisateurParId(6);
//				Utilisateur user2 = um.getUtilisateurParId(7);
//				Utilisateur user3 = um.getUtilisateurParId(8);
//		
//		Retrait ret1 = new Retrait("rue Jolie", "29000", "Quimper");
//		Retrait ret2 = new Retrait("impasse Vérité_2", "35000", "Rennes");
//		Retrait ret3 = new Retrait("couloir Zéro", "29400", "Brest");
//		
//		Categorie cat1 = cm.selectionnerCategorie(1);
//		Categorie cat2 = cm.selectionnerCategorie(2);
//		Categorie cat3 = cm.selectionnerCategorie(3);
//		
//		Article art1 = new Article("PC", "Commodore 64", sdf.parse("2021-01-20"), sdf.parse("2021-01-25"), 150, 0, "AV", cat1, user1, null);
//		Article art2 = new Article("Table", "En verre", sdf.parse("2021-01-20"), sdf.parse("2021-01-25"), 150, 0, "AV", cat2, user2, null);
//		Article art3 = new Article("Chemise", "À carreaux", sdf.parse("2021-01-20"), sdf.parse("2021-01-25"), 150, 0, "AV", cat3, user3, null);
//		
//		art1 = am.creerArticle(art1, ret1);		//user2 : impasse vérité
//		art2 = am.creerArticle(art2, ret2);		//user2 : impasse vérité
//		art3 = am.creerArticle(art3, ret3);		//user3 : bd mort
//		System.out.println("articles ok");		
		

		
//		Article a = am.selectionnerArticle(10);
//		System.out.println(a.toString());

		
//		List<Article> lstArt = am.listerArticles();
//		for(Article a : lstArt) {
//			System.out.println(a.toString());
//		}
		
	}

}
