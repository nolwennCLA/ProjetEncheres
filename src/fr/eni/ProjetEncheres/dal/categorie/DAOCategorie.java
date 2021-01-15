package fr.eni.ProjetEncheres.dal.categorie;

import java.util.List;

import fr.eni.ProjetEncheres.bo.Categorie;

public interface DAOCategorie {
	
	public Categorie insert(Categorie categorie) throws DAL_CategorieException;
	
	public List<Categorie> selectAll() throws DAL_CategorieException;
	
	public Categorie select(Integer noCategorie) throws DAL_CategorieException;
	
	public Categorie update(Categorie categorie) throws DAL_CategorieException;
	
	public void delete(Integer noCategorie) throws DAL_CategorieException;
	
}
