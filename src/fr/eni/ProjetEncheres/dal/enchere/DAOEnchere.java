package fr.eni.ProjetEncheres.dal.enchere;

import java.util.List;

import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public interface DAOEnchere {
	
	public Enchere insert(Enchere enchere) throws DAL_EnchereException;
	
	public List<Enchere> selectAll() throws DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL;
	
	public Enchere selectById(Integer noEnchere) throws DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL;
	
	public Enchere update(Enchere enchere) throws DAL_EnchereException;
	
	public void delete(Integer noEnchere) throws DAL_EnchereException;
	
	
	
	public List<Enchere> selectByNoArticle(Integer noArticle) throws DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL;
	
	
	
	
}
