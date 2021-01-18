package fr.eni.ProjetEncheres.dal.article;

import java.util.List;

import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public interface DAOArticle {
	
	public Article insert(Article article) throws DAL_ArticleException;
	
	public List<Article> selectAll() throws DAL_ArticleException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, UtilisateurExceptionBLL;
	
	public Article selectById(Integer noArticle) throws BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, UtilisateurExceptionBLL;
	
	public List<Article> selectByName(String nomArticle) throws DAL_ArticleException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, UtilisateurExceptionBLL;
	
	public List<Article> selectByCategory(Integer noCategorie) throws DAL_ArticleException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, UtilisateurExceptionBLL;
	
	public List<Article> selectByNameAndCategory(String nomArticle, Integer noCategorie) throws DAL_ArticleException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, UtilisateurExceptionBLL;
	
	public Article update(Article article) throws DAL_ArticleException;
	
	public void delete(Integer noArticle) throws DAL_ArticleException;
	
}
