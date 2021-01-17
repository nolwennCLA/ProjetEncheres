package fr.eni.ProjetEncheres.bll.enchere;

import java.util.List;

import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.retrait.BLL_RetraitException;
import fr.eni.ProjetEncheres.bll.utilisateur.UtilisateurExceptionBLL;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.enchere.EnchereDALException;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;

public interface EnchereManager {


	public List<Enchere> getListEnchere() throws EnchereExceptionBLL, UtilisateurExceptionBLL, BLL_ArticleException, DAL_ArticleException, BLL_RetraitException, DAL_RetraitException, BLL_CategorieException, DAL_CategorieException;
	
	public List<Enchere> getListEnchereParCategorie (Categorie libelle) throws EnchereExceptionBLL, UtilisateurExceptionBLL, BLL_ArticleException, DAL_ArticleException, BLL_RetraitException, DAL_RetraitException, BLL_CategorieException, DAL_CategorieException;
	
	public List<Enchere> getListEnchereParArticle (Article article) throws EnchereExceptionBLL, UtilisateurExceptionBLL, BLL_ArticleException, DAL_ArticleException, BLL_RetraitException, DAL_RetraitException, BLL_CategorieException, DAL_CategorieException;
	 
	public List<Enchere> getListEnchereOuvertes() throws EnchereExceptionBLL;
	
	public List<Enchere> getListMesEnchereEnCours() throws EnchereExceptionBLL;
	
	public List<Enchere> getListMesEnchereGagnees() throws EnchereExceptionBLL;
	
	public List<Enchere> getListMesVentesEnCours() throws EnchereExceptionBLL;
	
	public List<Enchere> getListMesVentesNonDebutees() throws EnchereExceptionBLL;
	
	public List<Enchere> getListMesVentesTerminees() throws EnchereExceptionBLL;
	
	public void addEnchere (Enchere enchere) throws EnchereExceptionBLL, EnchereDALException;
	
	public void validerEnchere(Enchere enchere) throws EnchereExceptionBLL;
	
	public void remporteVente(Enchere enchere) throws EnchereExceptionBLL;
	
	public Enchere selectionnerEnchere (Integer id) throws EnchereExceptionBLL;
	
	public void deleteEnchere (Integer id) throws EnchereExceptionBLL;

}
