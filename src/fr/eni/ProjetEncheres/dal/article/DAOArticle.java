package fr.eni.ProjetEncheres.dal.article;

import java.util.List;

import fr.eni.ProjetEncheres.bo.Article;

public interface DAOArticle {
	
	public Article insert(Article article) throws DAL_ArticleException;
	
	public List<Article> selectAll() throws DAL_ArticleException;
	
	public Article select(Integer noArticle) throws DAL_ArticleException;
	
	public Article update(Article article) throws DAL_ArticleException;
	
	public  void delete(Integer noArticle) throws DAL_ArticleException;
	
}
