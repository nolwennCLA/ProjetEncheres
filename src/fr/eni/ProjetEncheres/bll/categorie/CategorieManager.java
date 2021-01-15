package fr.eni.ProjetEncheres.bll.categorie;

import java.util.List;

import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;

public interface CategorieManager {
	
	public Categorie creerCategorie(Categorie categorie) throws BLL_CategorieException, DAL_CategorieException;
	
	public List<Categorie> listerCategories() throws BLL_CategorieException, DAL_CategorieException;
	
	public Categorie selectionnerCategorie(Integer noCategorie) throws BLL_CategorieException, DAL_CategorieException;
	
	public Categorie modifierCategorie(Categorie categorie) throws BLL_CategorieException, DAL_CategorieException;
	
	public void supprimerCategorie(Integer noCategorie) throws BLL_CategorieException, DAL_CategorieException;

}
