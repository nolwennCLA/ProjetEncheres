package fr.eni.ProjetEncheres.bll.retrait;

//import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.bll.article.ArticleManager;
import fr.eni.ProjetEncheres.bll.article.ArticleManagerSing;
import fr.eni.ProjetEncheres.bll.article.BLL_ArticleException;
import fr.eni.ProjetEncheres.bll.categorie.BLL_CategorieException;
import fr.eni.ProjetEncheres.bo.Article;
import fr.eni.ProjetEncheres.bo.Retrait;
import fr.eni.ProjetEncheres.dal.article.DAL_ArticleException;
import fr.eni.ProjetEncheres.dal.categorie.DAL_CategorieException;
import fr.eni.ProjetEncheres.dal.dal.DAOFactory;
import fr.eni.ProjetEncheres.dal.retrait.DAL_RetraitException;
import fr.eni.ProjetEncheres.dal.retrait.DAORetrait;

public class RetraitManagerImpl implements RetraitManager {
	
//	private List<Retrait> lstRet = new ArrayList<>();
	private DAORetrait retraitDao = DAOFactory.getDAORetrait();

	@Override
	public Retrait creerRetrait(Article article, Retrait retrait) throws BLL_RetraitException, DAL_RetraitException {
		
		if(article != null && retrait != null && retrait.getNoRetrait() == null) {
			
			//si l'adresse de retrait indiquée par l'utilisateur est différente de sa propre adresse
			if(!retrait.getRue().equalsIgnoreCase(article.getUtilisateur().getRue())
			|| !retrait.getCodePostal().equalsIgnoreCase(article.getUtilisateur().getCodePostal())
			|| !retrait.getVille().equalsIgnoreCase(article.getUtilisateur().getVille())) {
				
				boolean existeDeja = false;
					
				//si le retrait existe déjà, on ajoute le retrait à l'article
				for(Retrait r : this.listerRetraits()) {
					
					if(r.getRue().equalsIgnoreCase(retrait.getRue())
					&& r.getCodePostal().equals(retrait.getCodePostal())
					&& r.getVille().equals(retrait.getVille())) {
						
						existeDeja = true;
						article.setRetrait(this.selectionnerRetrait(r.getNoRetrait()));	
					} 
				}
				
				//si l'adresse de retrait n'existe pas, alors on crée un nouveau Retrait dans la base et on l'ajoute à l'article	
				if(!existeDeja) {
					retraitDao.insert(retrait);
					article.setRetrait(retrait);
				}
	
			//si l'adresse de retrait indiquée est l'adresse de l'utilisateur, on ajoute le retrait à l'Utilisateur
			//dans ce cas, le retrait n'est pas ajouté à la bdd et n'a donc pas de noRetrait (null)
			//NE RIEN FAIRE ICI SERAIT PEUT ÊTRE PLUS PROPRE
			} else {
				article.setRetrait(retrait);
			}
			
		} else {
			throw new BLL_RetraitException("BLL_RetraitException_creerRetrait() : Le Retrait ou l'Article est null, ou le Retrait existe déjà en base");
		}
			
		//on retourne le retrait
		return retrait;
	}

	@Override
	public List<Retrait> listerRetraits() throws BLL_RetraitException, DAL_RetraitException {
		return retraitDao.selectAll();
	}

	@Override
	public Retrait selectionnerRetrait(Integer noRetrait) throws BLL_RetraitException, DAL_RetraitException {
		Retrait retrait = null;
		if(noRetrait != null) {
			retrait = retraitDao.select(noRetrait);
		} else {
			throw new BLL_RetraitException("BLL_RetraitException_selectionnerRetrait() : Le noRetrait est null");
		}
		return retrait;
	}

	@Override
	public Retrait modifierRetrait(Retrait retrait) throws BLL_RetraitException, DAL_RetraitException {
		if(retrait.getNoRetrait() != null && retrait != null) {
			retrait = retraitDao.update(retrait);
		} else {
			throw new BLL_RetraitException("BLL_RetraitException_modifierRetrait() : Le Retrait n'existe pas ou son numéro est null");
		}
		return retrait;
	}

	@Override
	public void supprimerRetrait(Integer noRetrait) throws BLL_RetraitException, DAL_RetraitException {
		if(noRetrait != null) {
			retraitDao.delete(noRetrait);
		} else {
			throw new BLL_RetraitException("BLL_RetraitException_modifierRetrait() : Le noRetrait est null");
		}
		
		
	}

	

}
