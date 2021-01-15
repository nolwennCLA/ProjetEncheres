package fr.eni.ProjetEncheres.bll.enchere;

import java.util.List;

import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.bo.Enchere;
import fr.eni.ProjetEncheres.dal.enchere.EnchereDALException;

public interface EnchereManager {

//	create, read, update, delete


	public List<Enchere> getListEnchereNonConnect(Categorie categorie, Article article);

	public List<Enchere> getListEnchereConnect();

	public void faireEnchere (Enchere enchere) throws EnchereExceptionBLL, EnchereDALException;
	
	public void remporteVente(Enchere enchere) throws EnchereExceptionBLL;

}
