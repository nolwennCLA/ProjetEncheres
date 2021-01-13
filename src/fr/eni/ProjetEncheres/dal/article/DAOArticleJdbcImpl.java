package fr.eni.ProjetEncheres.dal.article;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import fr.eni.ProjetEncheres.bo.Article;

public class DAOArticleJdbcImpl implements DAOArticle {
	
	private final String INSERT = "INSERT INTO Articles "
			+ "(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, noUtilisateur, noCategorie)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	private final String SELECT_ALL = "SELECT * FROM Articles";
	
	private final String SELECT = "SELECT * FROM Articles WHERE noArticle = ?";
	
	private final String UPDATE = "UPDATE Articles SET"
			+ "nomArticle = ?,"
			+ "description = ?,"
			+ "dateDebutEncheres = ?,"
			+ "dateFinEncheres = ?,"
			+ "miseAPrix = ?,"
			+ "noUtilisateur = ?,"
			+ "noCategorie = ?"
			+ "WHERE noArticle = ?";
	
	private final String DELETE = "DELETE FROM Articles WHERE noArticle = ?";
			
	

	@Override
	public Article insert(Article article) throws DAL_ArticleException {
		
		return null;
	}

	@Override
	public List<Article> selectAll() throws DAL_ArticleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article select(Integer noArticle) throws DAL_ArticleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article update(Article article) throws DAL_ArticleException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer noArticle) throws DAL_ArticleException {
		// TODO Auto-generated method stub

	}

}
