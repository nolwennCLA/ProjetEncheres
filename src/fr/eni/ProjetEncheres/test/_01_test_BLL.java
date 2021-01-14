package fr.eni.ProjetEncheres.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import fr.eni.ProjetEncheres.bll.article.ArticleManager;
import fr.eni.ProjetEncheres.bll.article.ArticleManagerSing;
import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.retrait.RetraitManager;
import fr.eni.ProjetEncheres.bll.retrait.RetraitManagerSing;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.bo.Retrait;
import fr.eni.ProjetEncheres.bo.Utilisateur;

public class _01_test_BLL {
	
	

	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		ArticleManager am = ArticleManagerSing.getInstance();
		RetraitManager rm = RetraitManagerSing.getInstance();
		
		Utilisateur user = new Utilisateur("pseudo", "nom", "prenom", "@gmail", "06700", "rue des Palmiers", "29000", "Quimper", "1234");
		Categorie cat = new Categorie("Accessoire");
		Retrait ret = new Retrait("av.Goncourt", "35000", "Rennes");
		Retrait ret2 = new Retrait("rue des Palmiers", "29000", "Quimper");
		
		Article art = new Article("peigne", "peigne traditionnel", sdf.parse("2021-01-15") , sdf.parse("2021-01-20"), 150, user, cat);
		
		
		try {
			am.creerArticle(art, ret2);
		} catch (BLL_ArticleException | BLL_RetraitException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("LISTER ARTICLES");
		try {
			for(Article a : am.listerArticles()) {
				System.out.println(a.toString());
			}
		} catch (BLL_ArticleException e) {
			System.out.println(e.getMessage());
		}
		
		
		System.out.println("---------------");
		System.out.println("LISTER RETRAITS");
		try {
			for(Retrait r : rm.listerRetraits()) {
				System.out.println(r.toString());
			}
		} catch (BLL_RetraitException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
