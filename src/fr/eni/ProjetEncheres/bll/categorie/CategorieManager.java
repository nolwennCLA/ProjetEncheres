package fr.eni.ProjetEncheres.bll.categorie;

import java.util.List;

import fr.eni.ProjetEncheres.bo.Categorie;

public interface CategorieManager {
	
	public Categorie creerCategorie(Categorie categorie) throws BLL_CategorieException;
	
	public List<Categorie> listerCategories() throws BLL_CategorieException;
	
	public Categorie selectionnerCategorie(Integer noCategorie) throws BLL_CategorieException;
	
	public Categorie modifierCategorie(Categorie categorie) throws BLL_CategorieException;
	
	public void supprimerCategorie(Integer categorie) throws BLL_CategorieException;

}
