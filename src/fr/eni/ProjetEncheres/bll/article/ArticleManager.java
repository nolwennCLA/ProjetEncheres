package fr.eni.ProjetEncheres.bll.article;

import java.util.List;

import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Retrait;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public interface ArticleManager {
	
	public Article creerArticle(Article article, Retrait retrait) throws BLL_ArticleException, DAL_ArticleException, BLL_RetraitException, DAL_RetraitException;
	
	public List<Article> listerArticles() throws DAL_ArticleException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, UtilisateurExceptionBLL;
	
	public Article selectionnerArticleParId(Integer noArticle) throws BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL;
	
	public List<Article> selectionnerArticleParNom(String nomArticle) throws BLL_ArticleException, DAL_ArticleException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, UtilisateurExceptionBLL;
	
	public List<Article> selectionnerArticleParCategorie(Integer noCategorie) throws BLL_ArticleException, DAL_ArticleException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, UtilisateurExceptionBLL;
	
	public List<Article> selectionnerArticleParNomEtCategorie(String nomArticle, Integer noCategorie) throws BLL_ArticleException, DAL_ArticleException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, UtilisateurExceptionBLL;
	
	public Article modifierArticle(Article article) throws BLL_ArticleException, DAL_ArticleException;
	
	public void supprimerArticle(Integer noArticle) throws DAL_ArticleException, BLL_ArticleException;
	
}
