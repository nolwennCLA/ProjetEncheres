package fr.eni.ProjetEncheres.bll.article;

import java.util.List;

import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Retrait;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public interface ArticleManager {
	
	public Article creerArticle(Article article, Retrait retrait) throws BLL_ArticleException, BLL_RetraitException, DAL_ArticleException, DAL_RetraitException, UtilisateurExceptionBLL;
	
	public List<Article> listerArticles() throws BLL_ArticleException, DAL_ArticleException, UtilisateurExceptionBLL, BLL_RetraitException, DAL_RetraitException;
	
	public Article selectionnerArticle(Integer noArticle) throws BLL_ArticleException, DAL_ArticleException, UtilisateurExceptionBLL, BLL_RetraitException, DAL_RetraitException;
	
	public Article modifierArticle(Article article) throws UtilisateurExceptionBLL, BLL_RetraitException, DAL_ArticleException, BLL_ArticleException;
	
	public void supprimerArticle(Integer noArticle) throws BLL_ArticleException, DAL_ArticleException;
	
}
