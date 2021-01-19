package fr.eni.ProjetEncheres.test;

import java.util.List;

import fr.eni.ProjetEncheres.bll.article.ArticleManager;
import fr.eni.ProjetEncheres.bll.article.ArticleManagerSing;
import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public class _10_test_DAL_etatVente {

	public static void main(String[] args) throws DAL_ArticleException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, UtilisateurExceptionBLL, BLL_ArticleException {
		
		ArticleManager am = ArticleManagerSing.getInstance();
		List<Article> la = am.listerArticles();
		Article art;
		
//		for(Article a : la) {
//			System.out.println(a.getEtatVente());
//		}

//		art = am.selectionnerArticleParId(33);
//		System.out.println(art);
//		
//		art = am.selectionnerArticleParId(34);
//		System.out.println(art); 
		
		

	}

}
