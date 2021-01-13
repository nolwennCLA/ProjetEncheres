package fr.eni.ProjetEncheres.bll.article;

import java.util.List;

import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Retrait;

public interface ArticleManager {
	
	public Article creerArticle(Article article, Retrait retrait) throws BLL_ArticleException, BLL_RetraitException;
	
	public List<Article> listerArticles() throws BLL_ArticleException;
	
	public Article selectionnerArticle(Integer noArticle) throws BLL_ArticleException;
	
	public Article modifierArticle(Article article) throws BLL_ArticleException;
	
	public void supprimerArticle(Integer noArticle) throws BLL_ArticleException;
	
}
