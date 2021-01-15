package fr.eni.ProjetEncheres.bll.categorie;

import java.util.List;

import fr.eni.ProjetEncheres.bo.Categorie;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.categorie.DAOCategorie;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;

public class CategorieManagerImpl implements CategorieManager {
	
	
	DAOCategorie categorieDao = DAOFactory.getDAOCategorie();
	

	@Override
	public Categorie creerCategorie(Categorie categorie) throws BLL_CategorieException, DAL_CategorieException {
		return categorieDao.insert(categorie);
	}

	@Override
	public List<Categorie> listerCategories() throws BLL_CategorieException, DAL_CategorieException {
		return categorieDao.selectAll();
	}

	@Override
	public Categorie selectionnerCategorie(Integer noCategorie) throws BLL_CategorieException, DAL_CategorieException {
		return categorieDao.select(noCategorie);
	}

	@Override
	public Categorie modifierCategorie(Categorie categorie) throws BLL_CategorieException, DAL_CategorieException {
		return categorieDao.update(categorie);
	}

	@Override
	public void supprimerCategorie(Integer noCategorie) throws BLL_CategorieException, DAL_CategorieException {
		categorieDao.delete(noCategorie);
	}

}
