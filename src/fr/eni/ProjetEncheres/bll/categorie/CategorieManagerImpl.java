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
		if(categorie != null && categorie.getNoCategorie() == null) {
			categorie = categorieDao.insert(categorie);
		} else {
			throw new BLL_CategorieException("BLL_CategorieManagerImpl_creerCategorie() : La Categorie existe déjà en base ou est nulle");
		}
		return categorie;
		
	}

	@Override
	public List<Categorie> listerCategories() throws BLL_CategorieException, DAL_CategorieException {
		return categorieDao.selectAll();
	}

	@Override
	public Categorie selectionnerCategorie(Integer noCategorie) throws BLL_CategorieException, DAL_CategorieException {
		Categorie categorie = null;
		if(noCategorie != null) {
			categorie = categorieDao.select(noCategorie);
		} else {
			throw new BLL_CategorieException("BLL_CategorieManagerImpl_selectionnerCategorie() : Le noCategorie est null");
		}
		return categorie;
	}

	@Override
	public Categorie modifierCategorie(Categorie categorie) throws BLL_CategorieException, DAL_CategorieException {
		if(categorie.getNoCategorie() != null && categorie != null) {
			categorie = categorieDao.update(categorie);
		} else {
			throw new BLL_CategorieException("BLL_CategorieManagerImpl_modifierCategorie() : La Categorie n'existe pas ou son numéro est null");
		}
		return categorie;
	}

	@Override
	public void supprimerCategorie(Integer noCategorie) throws BLL_CategorieException, DAL_CategorieException {
		if(noCategorie != null) {
			categorieDao.delete(noCategorie);
		} else {
			throw new BLL_CategorieException("BLL_CategorieManagerImpl_supprimerCategorie() : Le noCategorie est null");
		}
	}

}
