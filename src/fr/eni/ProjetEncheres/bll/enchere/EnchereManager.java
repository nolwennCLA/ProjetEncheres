package fr.eni.ProjetEncheres.bll.enchere;

import java.util.List;

import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.enchere.DAL_EnchereException;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;


public interface EnchereManager {
	
	public Enchere creerEnchere(Enchere enchere) throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL;
	
	public List<Enchere> listerEncheres() throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL;
	
	public Enchere selectionnerEnchereParId(Integer noEnchere) throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL;
	
	//a priori inutile
	public Enchere modifierEnchere(Enchere enchere) throws BLL_EnchereException;
	
	public void supprimerEnchere(Integer noEnchere) throws BLL_EnchereException, DAL_EnchereException;
	
	
	
	public List<Enchere> selectionnerEnchereParNoArticle(Integer noArticle) throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL;
	
	public List<Enchere> selectionnerEncheresOuvertes() throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL;
	
	public List<Enchere> selectionnerEnchereParNomArticle(String nomArticle) throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL;
	
	public List<Enchere> selectionnerEnchereParCategorieArticle(Integer noCategorie) throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL;
	
	public List<Enchere> selectionnerEnchereParNomEtCategorieArticle(String nomArticle, Integer noCategorie) throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL;
	
	
	
	public boolean verifPossibiliteEnchere(Enchere enchere) throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL;
	
	public Enchere recupererDerniereEnchere(Enchere enchere) throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL;
	
	public void debitCredit(Enchere enchere) throws BLL_EnchereException, DAL_EnchereException, BLL_CategorieException, DAL_CategorieException, BLL_RetraitException, DAL_RetraitException, DAL_ArticleException, BLL_ArticleException, UtilisateurExceptionBLL;
	
}
