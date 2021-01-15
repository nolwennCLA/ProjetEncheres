package fr.eni.ProjetEncheres.test;

import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManager;
import fr.eni.ProjetEncheres.bll.categorie.CategorieManagerSing;
import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;

public class _03_test_ajoutCategories {

	public static void main(String[] args) throws BLL_CategorieException, DAL_CategorieException {
		
		CategorieManager cm = CategorieManagerSing.getInstance();
		
		cm.creerCategorie(new Categorie("Vêtement"));
		cm.creerCategorie(new Categorie("Maison"));
		cm.creerCategorie(new Categorie("Informatique"));
		cm.creerCategorie(new Categorie("Audiovisuel"));
		cm.creerCategorie(new Categorie("Jouets"));

		
	}

}
