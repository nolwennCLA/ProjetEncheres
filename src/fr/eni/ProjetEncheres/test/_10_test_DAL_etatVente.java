package fr.eni.ProjetEncheres.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

	public static void main(String[] args) throws DAL_ArticleException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, UtilisateurExceptionBLL, BLL_ArticleException, ParseException {
		
		ArticleManager am = ArticleManagerSing.getInstance();
		List<Article> la = am.listerArticles();
		Article a;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		a = am.selectionnerArticleParId(95);
		System.out.println(a);
		
		Date d1 = a.getDateFinEncheres();
		System.out.println(d1);
		
		cal.setTime(d1);
		
//		System.out.println(cal.get(Calendar.YEAR));
//		System.out.println(cal.get(Calendar.MONTH));
//		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
		
		Date d2 = new Date(sdf.parse(cal.get(Calendar.DAY_OF_MONTH)+1 + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.YEAR)).getTime());
		System.out.println(d2);
		
		



	}

}
