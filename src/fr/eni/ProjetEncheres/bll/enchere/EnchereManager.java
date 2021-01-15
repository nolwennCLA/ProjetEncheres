package fr.eni.ProjetEncheres.bll.enchere;

import java.util.List;

import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.dal.enchere.EnchereDALException;

public interface EnchereManager {


	public List<Enchere> getListEnchere() throws EnchereExceptionBLL;
	
	public List<Enchere> getListEnchereParCategorie (Categorie categorie) throws EnchereExceptionBLL;
	
	public List<Enchere> getListEnchereParArticle (Article article) throws EnchereExceptionBLL;
	
	public void addEnchere (Enchere enchere) throws EnchereExceptionBLL, EnchereDALException;
	
	public void validerEnchere(Enchere enchere) throws EnchereExceptionBLL;
	
	public void remporteVente(Enchere enchere) throws EnchereExceptionBLL;
	
	public Enchere selectionneEnchere (Integer id) throws EnchereExceptionBLL;

}
