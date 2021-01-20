package fr.eni.ProjetEncheres.test;

import java.util.Date;

import fr.eni.ProjetEncheres.bll.article.ArticleManager;
import fr.eni.ProjetEncheres.bll.article.ArticleManagerSing;
import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManager;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurManagerSingl;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Utilisateur;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public class TestUtilBLL {
	public static void main(String[] args) throws UtilisateurExceptionBLL, BLL_ArticleException, DAL_ArticleException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException {
		
		UtilisateurManager um= new UtilisateurManagerSingl().getInstance();
		ArticleManager am = new ArticleManagerSing().getInstance();
		
		//Utilisateur Vincent= new Utilisateur("vinc", "le bras", "vincent", "tyu", "  ", "lkj", "2580", "quimper", "password");
		//Utilisateur Julien= new Utilisateur("jul", "clerc", "julien", "lh", "  ", "lkj", "2580", "quimper", "password");
		//um.addUtilisateur(Vincent);
		//Vincent.setPseudo("hello");
		//um.updateUtilisateur(Vincent);
		//um.addUtilisateur(Julien);
//		Julien.setNom("lepers");
//		um.updateUtilisateur(Julien);
		//um.deleteUtilisateur(5);
		//System.out.println(um.getListUtilisateur());
		//Utilisateur Julie= new Utilisateur("jul", "jolie", "julie", "ffglflyufv", "  ", "lkj", "29750", "loctudy", "password");
		
		//um.addUtilisateur(Julie);
//		Utilisateur Capucine = new Utilisateur("la coquine", "Ric", "Capucine", "tyu", "022", "ljhl", "2147", "rennes", "doudou");
//		
//		um.addUtilisateur(Capucine);
		
		//System.out.println(um.getUtilisateurParPseudo("vinc"));
		//System.out.println(um.getUtilisateurParId(10));
		//um.deleteUtilisateur(17);
//		Utilisateur Herve = new Utilisateur("RV", "Ric", "Herve", "rv@gmail", "022", "ljhl", "2147", "rennes", "doudou");
//		um.addUtilisateur(Herve);
		
//		Utilisateur HerveEmail = new Utilisateur("bateau", "Rico", "RV", "rv@gmail", "022", "ljhl", "2147", "rennes", "doudou");
//		um.addUtilisateur(HerveEmail);
		
//		Utilisateur HervePseudo = new Utilisateur("RV", "Rico", "RV", "wanadoo", "022", "ljhl", "2147", "rennes", "doudou");
//		um.addUtilisateur(HervePseudo);
		
//		Utilisateur HerveCaractere = new Utilisateur("78ab", "Rico", "RV", "free", "022", "ljhl", "2147", "rennes", "doudou");
//		um.addUtilisateur(HerveCaractere);
		
		//um.deleteUtilisateur(37);
		
		//System.out.println(um.getUtilisateurParId(45));
		//System.out.println(um.getUtilisateurParPseudo("RV").getNoUtilisateur());
		
		//am.selectionnerArticleParNom("ordi");
		System.out.println(am.selectionnerArticleParId(1));
		
		
	}

}
